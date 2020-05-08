package com.moor.shelflyfe.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.gr.models.BookInfo
import com.moor.shelflyfe.ui.Author
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.BookDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BookDetailViewModel(val repository: BookRepository) : ViewModel() {


   fun getBookDetails(isbn:String)= liveData(viewModelScope.coroutineContext){
       emit(loadDetails(isbn))
   }

    suspend fun loadDetails(isbn:String):BookDetails = withContext(viewModelScope.coroutineContext){

          var detailsReq= async { repository.goodReadsService.getBookDetails(isbn).book }
          var volumeReq=  async { repository.googleBooksService.search("isbn:$isbn").items?.first()?.volumeInfo }

        var (details,volume)=Pair(detailsReq.await(),volumeReq.await())
        var author= details?.authors?.author!!
       return@withContext BookDetails(
           title = details?.title?:"",
           author = Author( details?.authors?.author?.id!!,
               name = author.name!!,
               imageUrl = author.image_url!!,
               rating = author.average_rating?.toFloat()?:0f
           ),
           description = details.description!!,
           imageUrl =  volume?.imageLinks?.thumbnail?.replace("http","https")?:"",
           publishedDate = volume?.publishedDate?:"",
           publisher = "",
           similarBooks = details.similar_books?.map { b-> Book(b.title?:"",b.authors?.author?.name?:"", b.isbn13!!,b.image_url?:"")  }?: emptyList(),
           rating = details.average_rating?.toFloatOrNull()?:0f


       );

    }

}
