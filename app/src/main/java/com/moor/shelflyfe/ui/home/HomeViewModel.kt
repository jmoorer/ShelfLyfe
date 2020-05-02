package com.moor.shelflyfe.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.nyt.models.Book

class HomeViewModel(var repository: BookRepository) : ViewModel() {
    // TODO: Implement the ViewModel
    private val bestSellers = MediatorLiveData<List<Book>>()


    init {
        loadBestSellers()
    }

    fun getBestSellers(): LiveData<List<Book>>{
        return bestSellers
    }

    fun loadBestSellers(){
        bestSellers.addSource(repository.getBestSellers()){books->
            bestSellers.postValue(books)
        }
    }
}
