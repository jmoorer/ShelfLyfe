package com.moor.shelflyfe.ui.explore

import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.ui.Section
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ExploreViewModel(var repository: BookRepository) : ViewModel() {

    var sections= MutableLiveData<List<Section>>()
    init {
        viewModelScope.launch {
            val ebooks = async {  Section("Top Ebooks",repository.getTopAudioBooks()!!.map { it.asBook() }) }
            val audioBooks = async { Section("Top Audiobooks",repository.getTopAudioBooks()!!.map { it.asBook() }) }
            sections.value = listOf(ebooks.await(),audioBooks.await())
        }
    }

    val featured= liveData {
        emit(repository.getBestSellers()!!.lists!!.flatMap { l-> l.bestSellers!! }
            .filter { b->b.rank==1 }
            .distinctBy { b->b.primaryIsbn13 }
            .map { b->b.asBook()}
        )
    }

    var bestSellerList= liveData {
        emit(repository.getBestSellerList().results)
    }


}
