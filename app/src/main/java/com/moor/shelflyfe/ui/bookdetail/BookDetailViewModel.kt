package com.moor.shelflyfe.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.moor.shelflyfe.api.BookRepository

class BookDetailViewModel(val repository: BookRepository) : ViewModel() {

   fun getBookDetails(isbn:String)= liveData {
       emit(repository.googleBooksService.search("isbn$isbn").items?.first())
   }

}
