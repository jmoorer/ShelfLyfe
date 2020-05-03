package com.moor.shelflyfe.api.itunes

import com.moor.shelflyfe.api.itunes.models.ItunesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ItunesService {
    companion object{
        val basrUrl="https://rss.itunes.apple.com/api/v1/us/"

    }

    @GET("books/top-paid/all/{count}/explicit.json")
    fun  getTopBooks(@Path("count")count:Int= 10):Call<ItunesResponse>

    @GET("audiobooks/top-audiobooks/all/{count}/explicit.json")
    fun  getTopAudioBooks(@Path("count")count:Int= 10):Call<ItunesResponse>


}