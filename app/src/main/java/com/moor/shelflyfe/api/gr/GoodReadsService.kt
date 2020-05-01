package com.moor.shelflyfe.api.gr


import com.moor.shelflyfe.api.gr.models.GoodreadsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GoodReadsService {

    @GET("book/isbn/{isbn}?format=xml")
    fun getBookDetails(@Path("isbn")isbn:String):Call<GoodreadsResponse>
}