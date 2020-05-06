package com.moor.shelflyfe.ui.explore

import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.Section
import kotlinx.coroutines.launch

class ExploreViewModel(var repository: BookRepository) : ViewModel() {


    var topAudioBooks= liveData {
        emit(repository.getTopAudioBooks()!!.map { it.asBook() })
    }
    var topEbooks=liveData {
        emit(repository.getTopBooks()!!.map { it.asBook() })
    }

    var sections = liveData {
        emit(Section("Top Ebooks",repository.getTopAudioBooks()!!.map { it.asBook() }))
        emit(Section("Top Audiobooks",repository.getTopAudioBooks()!!.map { it.asBook() }))
    }

    val featured= liveData {
        emit(repository.getBestSellers()!!.lists!!.flatMap { l-> l.bestSellers!! }
            .filter { b->b.rank==1 }
            .distinctBy { b->b.primaryIsbn13 }
            .map { b->b.asBook()}
        )
    }
}
