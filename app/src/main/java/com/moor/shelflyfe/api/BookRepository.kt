package com.moor.shelflyfe.api

import com.moor.shelflyfe.api.google.GoogleBooksService
import com.moor.shelflyfe.api.google.models.GoogleResponse
import com.moor.shelflyfe.api.gr.GoodReadsService
import com.moor.shelflyfe.api.gr.models.Author
import com.moor.shelflyfe.api.itunes.ItunesService
import com.moor.shelflyfe.api.itunes.models.ResultsItem
import com.moor.shelflyfe.api.nyt.NytService
import com.moor.shelflyfe.api.nyt.models.ListResult
import com.moor.shelflyfe.api.nyt.models.OverviewResult
import com.moor.shelflyfe.api.nyt.models.SellerListInfo
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.makeCall
import com.moor.shelflyfe.api.gr.models.Book as BookDetail

class BookRepository(
    val nytService: NytService,
    val goodReadsService: GoodReadsService,
    val googleBooksService: GoogleBooksService,
    val itunesService: ItunesService

) {


    suspend fun getTopBooks()=itunesService.getTopBooks().feed.results



    suspend fun getTopAudioBooks()=itunesService.getTopBooks().feed.results

    suspend fun getCategories()= nytService.getLists()

    suspend fun getBestSellers()= nytService.getOverview().results

    suspend fun  getBestSellerList(name:String)=nytService.getListByType(name).results

    suspend fun search(q:String,isbn:String?=null,author: String?=null,title:String?=null): GoogleResponse {
       return googleBooksService.search(q)
    }

//    fun getBookDetails(isbn:String):LiveData<BookDetail>{
//        val details= MutableLiveData<BookDetail>()
//        goodReadsService.getBookDetails(isbn).makeCall { throwable, response ->
//            response?.body()?.let{
//                details.value=it.book
//            }
//        }
//        return details
//    }

//    fun getAuthor(id:String):LiveData<Author>{
//        val author = MutableLiveData<Author>()
//        goodReadsService.getAuthorDetails(id).makeCall { throwable, response ->
//            response?.body()?.let {
//                author.value = it.author
//            }
//        }
//        return  author
//    }
}