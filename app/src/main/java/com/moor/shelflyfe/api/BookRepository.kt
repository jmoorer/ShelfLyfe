package com.moor.shelflyfe.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moor.shelflyfe.api.google.GoogleBooksService
import com.moor.shelflyfe.api.gr.GoodReadsService
import com.moor.shelflyfe.api.gr.models.Author
import com.moor.shelflyfe.api.nyt.NytService
import com.moor.shelflyfe.api.nyt.models.SellerListInfo
import com.moor.shelflyfe.api.nyt.models.Book as BestSeller
import com.moor.shelflyfe.makeCall
import com.moor.shelflyfe.api.gr.models.Book as BookDetail

class BookRepository(
    var nytService: NytService,
    val goodReadsService: GoodReadsService,
    val googleBooksService: GoogleBooksService

) {



    fun getCategories():LiveData<List<SellerListInfo>>{
        val lists= MutableLiveData<List<SellerListInfo>>()
        nytService.getLists().makeCall { throwable, response ->
            response?.body()?.let {
                lists.value=it.results?.filter {info->
                   info.updated=="MONTHLY"
                }
            }
        }
        return  lists
    }
    fun getBestSellers():LiveData<List<BestSeller>>{
        val books=MutableLiveData<List<BestSeller>>()
        nytService.getOverview().makeCall { throwable, response ->
            response?.body()?.let {
                it.results?.lists?.let {
                    books.value = it.filter { l->l.listNameEncoded!!.contains("combined") }
                        .flatMap {l->l.books!!}
                        .distinctBy { it.primaryIsbn13 }
                        .sortedBy { it.rank }
                }
            }
        }
        return books
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