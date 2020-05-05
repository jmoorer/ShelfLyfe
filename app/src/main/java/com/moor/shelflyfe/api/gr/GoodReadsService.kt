package com.moor.shelflyfe.api.gr


import com.moor.shelflyfe.api.gr.models.AuthorResponse
import com.moor.shelflyfe.api.gr.models.BookResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GoodReadsService {

    companion object{
        val baseUrl="https://www.goodreads.com/"
    }

    @GET("book/isbn/{isbn}?format=xml")
    suspend fun getBookDetails(@Path("isbn")isbn:String):BookResponse


    @GET("author/show.xml")
    suspend fun getAuthorDetails(@Query("id")id:String):AuthorResponse
}
