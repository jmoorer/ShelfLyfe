package com.moor.shelflyfe.api

import com.moor.shelflyfe.api.google.GoogleBooksService
import com.moor.shelflyfe.api.google.models.GoogleResponse
import com.moor.shelflyfe.api.itunes.ItunesService
import com.moor.shelflyfe.api.nyt.NytService
import com.moor.shelflyfe.api.openlib.OpenLibService
import com.moor.shelflyfe.api.openlib.models.DataResponse
import com.moor.shelflyfe.api.openlib.models.DetailResponse

class BookRepository(
    val nytService: NytService,
    val googleBooksService: GoogleBooksService,
    val itunesService: ItunesService,
    var openLibService: OpenLibService

) {


    suspend fun getTopBooks(genreId:String="38", size:Int=10)= itunesService.getTopBooks(genreId,size)

    suspend fun getTopAudioBooks(size:Int=10)=itunesService.getTopAudioBooks(size)

    suspend fun getBestSellerList()= nytService.getLists()

    suspend fun getBestSellers()= nytService.getOverview().results

    suspend fun  getBestSellerList(name:String)=nytService.getListByType(name).results

    suspend fun search(q:String,order:String="relevance"): GoogleResponse {
       return googleBooksService.search(q,order)
    }

    suspend fun getBookDetails(isbn:String): DataResponse {
        return openLibService.getBookDataByIsbn(isbn)
    }

    suspend fun getSubjectDetails(subject:String) = openLibService.getSubjectDetails(subject)




}