package com.moor.shelflyfe.api

import com.moor.shelflyfe.api.google.GoogleBooksService
import com.moor.shelflyfe.api.google.models.GoogleResponse
import com.moor.shelflyfe.api.gr.GoodReadsService
//import com.moor.shelflyfe.api.gr.models.AuthorResponse
import com.moor.shelflyfe.api.gr.models.BookResponse
import com.moor.shelflyfe.api.itunes.ItunesService
import com.moor.shelflyfe.api.nyt.NytService
import com.moor.shelflyfe.api.openlib.OpenLibService

class BookRepository(
    val nytService: NytService,
    val goodReadsService: GoodReadsService,
    val googleBooksService: GoogleBooksService,
    val itunesService: ItunesService,
    var openLibService: OpenLibService

) {


    suspend fun getTopBooks(genreId:String="38", size:Int=10)= itunesService.getTopBooks(genreId,size)

    suspend fun getTopAudioBooks(size:Int=10)=itunesService.getTopAudioBooks(size)

    suspend fun getBestSellerList()= nytService.getLists()

    suspend fun getBestSellers()= nytService.getOverview().results

    suspend fun  getBestSellerList(name:String)=nytService.getListByType(name).results

    suspend fun search(q:String): GoogleResponse {
       return googleBooksService.search(q)
    }

    suspend fun getBookDetails(isbn:String): BookResponse {
        return goodReadsService.getBookDetailsByIsbn(isbn)
    }

//    suspend fun getAuthor(id:String): AuthorResponse {
//        return goodReadsService.getAuthorDetails(id)
//    }


}