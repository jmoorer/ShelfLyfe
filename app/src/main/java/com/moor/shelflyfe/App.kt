package com.moor.shelflyfe

import android.app.Application
import com.google.gson.Gson
import com.moor.shelflyfe.api.google.GoogleBooksService
import com.moor.shelflyfe.api.itunes.ItunesService
import com.moor.shelflyfe.api.itunes.models.GenereMap
import com.moor.shelflyfe.api.itunes.models.ItunesGenre
import com.moor.shelflyfe.db.Genre
import com.moor.shelflyfe.db.ObjectBox
import com.moor.shelflyfe.db.Trending
import com.moor.shelflyfe.di.applicationModule
import io.objectbox.Box
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.get
import org.koin.android.ext.android.startKoin
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.nio.charset.Charset


class App : Application(){

    private lateinit var trending: Box<Trending>
    private lateinit var genreBox: Box<Genre>


    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(androidContext = this@App, modules = listOf(applicationModule))

        ObjectBox.init(this)


        genreBox=ObjectBox.boxStore.boxFor(Genre::class.java)
        trending= ObjectBox.boxStore.boxFor(Trending::class.java)

        GlobalScope.launch(Dispatchers.Main) {
            val itunes:ItunesService= get()
            val genreMap=itunes.getGenreById("38")
            genreMap["38"]?.let { storeGenre(it) }
            val titles= itunes.getTopBooks("38",20).entry?.map{ it.title}
            val query = titles?.map {"intitle:\"${it}\""}?.joinToString("|")
            query?.let {
//                var googleBooksService:GoogleBooksService=get()
//                var books=googleBooksService.search(query).items?.filter { titles.contains(it.volumeInfo.title) }
////                ?.forEach {
////                    Trending()
////                }
                print("")
            }




        }


    }

    private fun storeGenre(genre:ItunesGenre): Genre? {

        val g=genreBox.get(genre.id.toLong())
        if (g!=null)
            return g

        val entity=  Genre(
            id = genre.id.toLong(),
            name = genre.name
        )
        genreBox.put(entity)
        genre.subgenres?.values?.forEach{
            val sub = storeGenre(it)
            sub?.let {
                entity.subGenres.add(sub)
            }
        }

        genreBox.put(entity)
        return entity
    }
}