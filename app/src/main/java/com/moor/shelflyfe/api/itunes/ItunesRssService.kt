package com.moor.shelflyfe.api.itunes

import com.moor.shelflyfe.api.itunes.models.ItunesGenre
import com.moor.shelflyfe.api.itunes.models.ItunesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItunesRssService {
    companion object{
        val basrUrl="https://rss.itunes.apple.com/api/v1/us/"

    }

    @GET("books/top-paid/all/{count}/explicit.json")
    suspend fun  getTopBooks(@Path("count")count:Int):ItunesResponse

    @GET("audiobooks/top-audiobooks/all/{count}/explicit.json")
    suspend fun  getTopAudioBooks(@Path("count")count:Int):ItunesResponse




}