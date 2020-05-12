package com.moor.shelflyfe.ui.bookdetail

import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.google.models.VolumeInfo
import com.moor.shelflyfe.api.gr.models.BookInfo
import com.moor.shelflyfe.db.Favorite
import com.moor.shelflyfe.db.Favorite_
import com.moor.shelflyfe.db.ObjectBox
import com.moor.shelflyfe.openLibCoverUrl
import com.moor.shelflyfe.ui.Author
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.BookDetails
import io.objectbox.android.AndroidScheduler
import io.objectbox.android.ObjectBoxDataSource
import io.objectbox.android.ObjectBoxLiveData
import io.objectbox.query.Query
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BookDetailViewModel(val repository: BookRepository) : ViewModel() {

    private var query: Query<Favorite>?=null
    private var bookDetails = MutableLiveData<BookDetails>()
    private val isFavorite = MutableLiveData<Boolean>()
    private val favoriteBox = ObjectBox.boxStore.boxFor(Favorite::class.java)


    fun isFavorited(isbn:String): LiveData<Boolean> {
        this.query=favoriteBox.query().equal(Favorite_.isbn13,isbn).build()
        return Transformations.switchMap(ObjectBoxLiveData(query)){
            isFavorite.postValue(it.any())
            return@switchMap isFavorite
        }
    }
   fun getBookDetails(isbn: String): LiveData<BookDetails> {
       viewModelScope.launch {
           val details=loadDetails(isbn)
           details?.let { bookDetails.value =details }
       }
       return bookDetails
   }

    fun  toggleFavorite(){
        if(isFavorite.value==true){
            query?.remove()
        }else{
            val book= bookDetails.value!!
            favoriteBox.put(
                Favorite(
                    title = book.title,
                    author = "",
                    isbn13 = book.isbn,
                    imageUrl = book.imageUrl?:"",
                    rating =  book.rating
                ))
        }

    }


    private suspend fun loadDetails(isbn: String):BookDetails? = withContext(viewModelScope.coroutineContext){
        val key = "ISBN:${isbn}"
        var details = repository.openLibService.getBooksByIsbn(key)[key]?.details
        var query="isbn:${isbn}"
        val volume:VolumeInfo= repository.search("isbn:${isbn}").items?.first()?.volumeInfo!!

       return@withContext BookDetails(
           isbn=isbn,
           title = details?.title?:volume.title,
           author = details?.authors?.map { it.name }?:volume.authors?: emptyList(),
           description = details?.description?:volume.description,
           imageUrl = details?.covers?.first()?.let { openLibCoverUrl(it) }?:volume.imageLinks?.thumbnail?.replace("http", "https"),
           publishedDate =volume.publishedDate,
           publisher = volume.publisher,
           rating = volume.averageRating?:0f,
           similarBooks = emptyList()
           //bookDetails.similar_books?.map { s-> Book(s.title!!,s.author!!,s.isbn13!!,s.image_url!!)}?: emptyList()
       );

    }

}
