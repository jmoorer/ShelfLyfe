package com.moor.shelflyfe

import android.app.Application
import android.content.Context
import com.moor.shelflyfe.api.itunes.ItunesService
import com.moor.shelflyfe.api.openlib.OpenLibService
import com.moor.shelflyfe.db.Genre
import com.moor.shelflyfe.db.ObjectBox
import com.moor.shelflyfe.db.Trending
import com.moor.shelflyfe.di.applicationModule
import io.objectbox.Box
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import org.koin.android.ext.android.get
import org.koin.android.ext.android.startKoin
import java.lang.Exception
import java.util.*


class App : Application(){


    private lateinit var trendingBox: Box<Trending>
    private lateinit var genreBox: Box<Genre>

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(androidContext = this@App, modules = listOf(applicationModule))

        ObjectBox.init(this)

        genreBox=ObjectBox.boxStore.boxFor(Genre::class.java)
        trendingBox=ObjectBox.boxStore.boxFor(Trending::class.java)
        if(genreBox.count()<1){
            var stream=assets.open("genres.json")
            val json = stream.bufferedReader().use{it.readText()}
            val reader = JSONArray(json)
            for (i in 0 until reader.length()){
                val o = reader.getJSONObject(i)
                genreBox.put( Genre(key=o.getString("key"),name = o.getString("name")))
            }
        }

        val prefs = this.getSharedPreferences(this.javaClass.name, Context.MODE_PRIVATE)
        val lastSync = prefs.getLong(LASTSYNC,0);
        val date = Date()
        date.setHours(date.getHours() + 24)
        if(lastSync < System.currentTimeMillis()){

            GlobalScope.launch {
                try {
                    val itunesService=get<ItunesService>()
                    val openLibService= get<OpenLibService>()
                    val books=itunesService.getTopBooks("38",200).entry?.map { it.asBook() }
                    val isbnsKeys= books?.filter { !it.isbn.isNullOrBlank() }?.map { "ISBN:${it.isbn}" }?.joinToString(",")?:""
                    val found=openLibService.getBookByIsbn(isbnsKeys)


                    val trending= books?.filter {found.contains("ISBN:${it.isbn}")}?.map {book->
                        Trending(
                            title = book.title,
                            author = book.author,
                            isbn13 = book.isbn!!,
                            imageUrl =  book.imageUrl?:""
                        )
                    }
                    prefs.edit().putLong(LASTSYNC,date.time).apply()
                    ObjectBox.boxStore.runInTx{
                        trendingBox.removeAll()
                        trendingBox.put(trending)
                    }
                }catch (ex:Exception){
                    
                }

            }
        }




    }

    companion object {
        const val LASTSYNC="LASTSYNC"
    }


}