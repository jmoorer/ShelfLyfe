package com.moor.shelflyfe.api.openlib

import com.moor.shelflyfe.api.openlib.models.OpenLibDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibService {
    companion object{
        val baseUrl="https://openlibrary.org/api/"
    }

    @GET("books?jscmd=details&format=json")
    suspend fun  getBookDetailsByIsbn(@Query("bibkeys")keys:String):OpenLibDetailResponse

    @GET("books?jscmd=data&format=json")
    suspend fun  getBookDataByIsbn(@Query("bibkeys")keys:String):OpenLibDetailResponse
}