package com.moor.shelflyfe.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.nyt.models.Book
import com.moor.shelflyfe.api.nyt.models.SellerListInfo

class HomeViewModel(var repository: BookRepository) : ViewModel() {

    private val bestSellers = MediatorLiveData<List<Book>>()
    private val categories = MediatorLiveData<List<SellerListInfo>>()

    init {
        loadBestSellers()
        loadCategories()
    }

    fun getBestSellers(): LiveData<List<Book>>{
        return bestSellers
    }

    fun getCategories():LiveData<List<SellerListInfo>>{
        return categories
    }

    fun loadCategories(){
        categories.addSource(repository.getCategories()){cs->
            categories.postValue(cs)
        }
    }
    fun loadBestSellers(){
        bestSellers.addSource(repository.getBestSellers()){books->
            bestSellers.postValue(books)
        }
    }
}
