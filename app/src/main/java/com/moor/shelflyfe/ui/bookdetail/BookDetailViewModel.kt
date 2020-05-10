package com.moor.shelflyfe.ui.bookdetail

import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.google.models.VolumeInfo
import com.moor.shelflyfe.api.gr.models.BookInfo
import com.moor.shelflyfe.db.Favorite
import com.moor.shelflyfe.db.Favorite_
import com.moor.shelflyfe.db.ObjectBox
import com.moor.shelflyfe.ui.Author
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.BookDetails
import io.objectbox.android.AndroidScheduler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BookDetailViewModel(val repository: BookRepository) : ViewModel() {

    private var bookDetails = MutableLiveData<BookDetails>()
    val isFavorite = MutableLiveData<Boolean>()
    private val favoriteBox = ObjectBox.boxStore.boxFor(Favorite::class.java)

    fun isFavorited(isbn:String): LiveData<Boolean> {

        favoriteBox.query().build().subscribe().on(AndroidScheduler.mainThread()).observer{ fvs->
            isFavorite.value= fvs.any { it.isbn13==isbn }
        }
        return isFavorite
    }
   fun getBookDetails(book: Book): LiveData<BookDetails> {
       viewModelScope.launch {
           val details=loadDetails(book)
           details?.let { bookDetails.value =details }
       }
       return bookDetails
   }

    fun  toggleFavorite(book:Book){
        if(isFavorite.value==true){
            favoriteBox.query().equal(Favorite_.isbn13,book.isbn).build().remove()
        }else{
            favoriteBox.put(
                Favorite(
                    title = book.title,
                    author = book.author,
                    isbn13 = book.isbn!!,
                    imageUrl = book.imageUrl?:""
                ))
        }

    }


    private suspend fun loadDetails(book: Book):BookDetails? = withContext(viewModelScope.coroutineContext){
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
           isbn= volume.industryIdentifiers?.first()?.identifier?:bookDetails.isbn13!!,
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
