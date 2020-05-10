package com.moor.shelflyfe.api.itunes

import com.moor.shelflyfe.api.itunes.models.ItunesGenre
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesService {
    companion object {
        val basrUrl = "https://itunes.apple.com/"

    }

    @GET("WebObjects/MZStoreServices.woa/ws/genres")
    suspend fun getGenreById(@Query("id") id: String): Map<String, ItunesGenre>

}