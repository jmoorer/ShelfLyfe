package com.moor.shelflyfe

import android.app.Application
import com.google.gson.Gson
import com.moor.shelflyfe.api.itunes.models.GenereMap
import com.moor.shelflyfe.api.itunes.models.ItunesGenre
import com.moor.shelflyfe.di.applicationModule
import org.koin.android.ext.android.startKoin
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


class App : Application(){

    companion object{
        var GenreMap:Map<String,ItunesGenre> = HashMap()
    }
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(androidContext = this@App, modules = listOf(applicationModule))
        var json: String? = null
         try {
             val jsonString = this.assets.open("genres.json").bufferedReader().use { it.readText() }
             val gson = Gson()
             GenreMap = gson.fromJson(jsonString, GenereMap::class.java)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
        print("")

    }
}