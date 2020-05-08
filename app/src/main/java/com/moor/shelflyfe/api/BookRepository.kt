package com.moor.shelflyfe.api

import com.moor.shelflyfe.api.google.GoogleBooksService
import com.moor.shelflyfe.api.google.models.GoogleResponse
import com.moor.shelflyfe.api.gr.GoodReadsService
//import com.moor.shelflyfe.api.gr.models.AuthorResponse
import com.moor.shelflyfe.api.gr.models.BookResponse
import com.moor.shelflyfe.api.itunes.ItunesService
import com.moor.shelflyfe.api.nyt.NytService

class BookRepository(
    val nytService: NytService,
    val goodReadsService: GoodReadsService,
    val googleBooksService: GoogleBooksService,
    val itunesService: ItunesService

) {


    suspend fun getTopBooks()=itunesService.getTopBooks().feed.results

    suspend fun getTopAudioBooks()=itunesService.getTopBooks().feed.results

    suspend fun getBestSellerList()= nytService.getLists()

    suspend fun getBestSellers()= nytService.getOverview().results

    suspend fun  getBestSellerList(name:String)=nytService.getListByType(name).results

    suspend fun search(q:String,isbn:String?=null,author: String?=null,title:String?=null): GoogleResponse {
       return googleBooksService.search(q)
    }

    suspend fun getBookDetails(isbn:String): BookResponse {
       var r= goodReadsService.getBookDetails2(isbn)
        return goodReadsService.getBookDetails(isbn)
    }

//    suspend fun getAuthor(id:String): AuthorResponse {
//        return goodReadsService.getAuthorDetails(id)
//    }


}