package com.moor.shelflyfe.ui.booklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.ui.Book
import kotlinx.coroutines.launch

class BookListViewModel(val repository: BookRepository) : ViewModel() {

    val books = MutableLiveData<List<Book>>()


    fun setBooks(data:List<Book>){
        books.postValue(data)
    }

    fun getBookList():LiveData<List<Book>> =books

    fun  loadBooksByGenre(genre: String){
        viewModelScope.launch {
            setBooks(emptyList())
            val books=repository.search( "subject:${genre}" ,"newest").items?.map { it.asBook() }
            books?.let { setBooks(it) }
        }
    }


    fun  loadBooksByBestSellerList(name: String){
        viewModelScope.launch {
            setBooks(emptyList())
            var books=repository.getBestSellerList(name)?.bestSellers?.map { it.asBook() }
            books?.let { setBooks(it) }
        }
    }



}
