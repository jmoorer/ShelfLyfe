package com.moor.shelflyfe.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.ui.Book
import kotlinx.coroutines.launch

class SearchViewModel(var repository: BookRepository) : ViewModel() {

    private var results= MutableLiveData<List<Book>>()

    fun searchResults() : LiveData<List<Book>> = results

    fun search(q:String)=viewModelScope.launch {
        val books=repository.search(q).items!!.map { it.asBook() }
        results.postValue(books)
    }

}
