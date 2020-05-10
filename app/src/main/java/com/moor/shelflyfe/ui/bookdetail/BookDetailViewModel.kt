package com.moor.shelflyfe.ui.bookdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.google.models.VolumeInfo
import com.moor.shelflyfe.api.gr.models.BookInfo
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.ui.Author
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.BookDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class BookDetailViewModel(val repository: BookRepository) : ViewModel() {


   fun getBookDetails(book: Book)= liveData(viewModelScope.coroutineContext){
       val details=loadDetails(book)
       details?.let { emit(details) }
   }

    suspend fun loadDetails(book: Book):BookDetails? = withContext(viewModelScope.coroutineContext){
        var bookDetails:BookInfo?=null
        val title= book.title.split("(Unabridged)").first()
        var query="(intitle:${title}+inauthor:${book.author})|isbn:${book.isbn}"
        val volume:VolumeInfo= repository.search(query).items?.first()?.volumeInfo!!
        if(!book.isbn.isNullOrEmpty()){
            try {
                bookDetails= repository.goodReadsService.getBookDetailsByIsbn(book.isbn).book!!
            }catch (error:Exception){ }
        }
        if (bookDetails==null){
            val hit = repository.goodReadsService.searchDetails(title).results?.first()
            bookDetails= repository.goodReadsService.getBookDetailsById(hit!!.id!!).book!!
        }
        print("")
       return@withContext BookDetails(
           title = volume.title,
           author = Author(
               id= bookDetails.authors?.author?.id,
               name =  bookDetails.authors?.author?.name?:"Unknown",
               imageUrl = bookDetails.authors?.author?.image_url,
               rating = bookDetails.authors?.author?.average_rating?.toFloat()?:0f
           ),
           description = volume.description,
           imageUrl = volume.imageLinks?.thumbnail?.replace("http", "https"),
           publishedDate =volume.publishedDate,
           publisher = volume.publisher,
           rating = volume.averageRating?:0f,
           similarBooks = bookDetails.similar_books?.map { s-> Book(s.title!!,s.author!!,s.isbn13!!,s.image_url!!)}?: emptyList()
       );

    }

}
