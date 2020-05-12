package com.moor.shelflyfe.api.itunes

import com.moor.shelflyfe.api.itunes.models.Feed
import com.moor.shelflyfe.api.itunes.models.ItunesGenre
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ItunesService {
    companion object {
        val basrUrl = "https://itunes.apple.com/"

    }

    @GET("WebObjects/MZStoreServices.woa/ws/genres")
    suspend fun getGenreById(@Query("id") id: String): Map<String, ItunesGenre>

    @GET("us/rss/topebooks/genre={id}/limit={limit}/xml")
    suspend fun getTopBooks(@Path("id")id:String, @Path("limit")limit:Int): Feed

    @GET("us/rss/topaudiobooks/limit={limit}/xml")
    suspend fun getTopAudioBooks(@Path("limit")limit:Int): Feed


}