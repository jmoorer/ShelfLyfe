package com.moor.shelflyfe.api.google

import com.moor.shelflyfe.api.google.models.GoogleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoogleBooksService {
    companion object{
        val baseUrl="https://www.googleapis.com/"
    }

    @GET("books/v1/volumes?maxResults=40")
   suspend fun search(@Query("q") q:String):GoogleResponse

}