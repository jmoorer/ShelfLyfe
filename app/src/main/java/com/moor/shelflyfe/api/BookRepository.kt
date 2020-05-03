package com.moor.shelflyfe.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moor.shelflyfe.api.google.GoogleBooksService
import com.moor.shelflyfe.api.gr.GoodReadsService
import com.moor.shelflyfe.api.gr.models.Author
import com.moor.shelflyfe.api.itunes.ItunesService
import com.moor.shelflyfe.api.itunes.models.ResultsItem
import com.moor.shelflyfe.api.nyt.NytService
import com.moor.shelflyfe.api.nyt.models.ListResult
import com.moor.shelflyfe.api.nyt.models.OverviewResult
import com.moor.shelflyfe.api.nyt.models.SellerListInfo
import com.moor.shelflyfe.makeCall
import com.moor.shelflyfe.api.gr.models.Book as BookDetail

class BookRepository(
    val nytService: NytService,
    val goodReadsService: GoodReadsService,
    val googleBooksService: GoogleBooksService,
    val itunesService: ItunesService

) {


    fun getTopBooks():LiveData<List<ResultsItem>>{
        val books= MutableLiveData<List<ResultsItem>>()
        itunesService.getTopBooks().makeCall { throwable, response ->
            books.value= response?.body()?.feed?.results
        }
        return  books
    }

    fun getTopAudioBooks():LiveData<List<ResultsItem>>{
        val books= MutableLiveData<List<ResultsItem>>()
        itunesService.getTopAudioBooks().makeCall { throwable, response ->
            books.value= response?.body()?.feed?.results
        }
        return  books
    }

    fun getCategories():LiveData<List<SellerListInfo>>{
        val lists= MutableLiveData<List<SellerListInfo>>()
        nytService.getLists().makeCall { throwable, response ->
            lists.value=response?.body()?.results
        }
        return  lists
    }
    fun getBestSellers():LiveData<OverviewResult>{
        val overview=MutableLiveData<OverviewResult>()
        nytService.getOverview().makeCall { throwable, response ->
          overview.value= response?.body()?.results
        }
        return overview
    }

    fun  getBestSellerList(name:String):LiveData<ListResult>{
        val results = MutableLiveData<ListResult>()
        nytService.getListByType(name).makeCall { throwable, response ->
            results.value= response?.body()?.results
        }
        return results
    }

    fun getBookDetails(isbn:String):LiveData<BookDetail>{
        val details= MutableLiveData<BookDetail>()
        goodReadsService.getBookDetails(isbn).makeCall { throwable, response ->
            response?.body()?.let{
                details.value=it.book
            }
        }
        return details
    }

    fun getAuthor(id:String):LiveData<Author>{
        val author = MutableLiveData<Author>()
        goodReadsService.getAuthorDetails(id).makeCall { throwable, response ->
            response?.body()?.let {
                author.value = it.author
            }
        }
        return  author
    }
}