package com.moor.shelflyfe.ui.search

import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.db.Genre
import com.moor.shelflyfe.db.Genre_
import com.moor.shelflyfe.db.ObjectBox
import com.moor.shelflyfe.ui.Book
import io.objectbox.android.ObjectBoxLiveData
import kotlinx.coroutines.launch

class SearchViewModel(var repository: BookRepository) : ViewModel() {

    private var results= MutableLiveData<List<Book>>()
    private var genreBox= ObjectBox.boxStore.boxFor(Genre::class.java)

    fun searchResults() : LiveData<List<Book>> = results

    fun search(q:String)=viewModelScope.launch {
        val books=repository.search(q).items!!.map { it.asBook() }
        results.postValue(books)
    }

    fun getGenres() = Transformations.map(ObjectBoxLiveData(genreBox.query().equal(Genre_.id,38).build())){ genres->
        genres.first().subGenres.toList().sortedBy{ it.name }
    }

}
