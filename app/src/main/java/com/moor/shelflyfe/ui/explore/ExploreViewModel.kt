package com.moor.shelflyfe.ui.explore

import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.ui.Book
import kotlinx.coroutines.launch

class ExploreViewModel(var repository: BookRepository) : ViewModel() {

    private var results= MutableLiveData<List<Book>>()

    fun searchResults() :LiveData<List<Book>> = results

    fun search(q:String)=viewModelScope.launch {
        val books=repository.search(q).items!!.map { it.asBook() }
        results.postValue(books)
    }

    var topAudioBooks= liveData {
        emit(repository.getTopAudioBooks()!!.map { it.asBook() })
    }
    var topEbooks=liveData {
        emit(repository.getTopBooks()!!.map { it.asBook() })
    }

    val featured= liveData {
        emit(repository.getBestSellers()!!.lists!!.flatMap { l-> l.bestSellers!! }
            .filter { b->b.rank==1 }
            .distinctBy { b->b.primaryIsbn13 }
            .map { b->b.asBook()}
        )
    }
}
