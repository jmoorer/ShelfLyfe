package com.moor.shelflyfe.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moor.shelflyfe.api.gr.GoodReadsService
import com.moor.shelflyfe.api.nyt.NytService
import com.moor.shelflyfe.api.nyt.models.Book
import com.moor.shelflyfe.makeCall

class BookRepository(var nytService: NytService,val goodReadsService: GoodReadsService) {



    fun getBestSellers():LiveData<List<Book>>{
        val books=MutableLiveData<List<Book>>()
        nytService.getOverview().makeCall { throwable, response ->
            response?.body()?.let {
                books.value = it.results?.lists?.flatMap {l->l.books!!}?.distinctBy { it.primaryIsbn13 }
            }
        }
        return books
    }
}