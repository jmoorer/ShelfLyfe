package com.moor.shelflyfe.ui.explore

import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.toGenre
import com.moor.shelflyfe.ui.Category
import com.moor.shelflyfe.ui.Section
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ExploreViewModel(var repository: BookRepository) : ViewModel() {

    var sections= MutableLiveData<List<Section>>()
    var genre = MutableLiveData<Genre>()
    init {
        viewModelScope.launch {
            val ig = async { repository.getItunesGenre() }
            val ebooks = async {  Section("Top Ebooks",repository.getTopAudioBooks()!!.map { it.asBook() }) }
            val audioBooks = async { Section("Top Audiobooks",repository.getTopAudioBooks()!!.map { it.asBook() }) }
            sections.value = listOf(ebooks.await(),audioBooks.await())
            genre.value = ig.await().entries.map { it.value.toGenre() }.first()
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
    var trendingCategories= liveData {
       var res= repository.itunesService.getGenreById("38")
        val categories= repository.getTopBooks(100)
            ?.flatMap { e->e.genres!! }
            ?.asSequence()
            ?.groupBy { g->g.genreId }
            ?.map { p->p }
            ?.sortedByDescending { p->p.value.size }
            ?.map { p->p.value.first() }
            ?.distinctBy { g->g.genreId }
            ?.map { g->Category(g.genreId,g.name) }
            ?.toList()
        emit(categories)

    }


}
