package com.moor.shelflyfe.ui.explore

import android.text.Html
import android.text.TextUtils
import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.db.ObjectBox
import com.moor.shelflyfe.db.Trending
import com.moor.shelflyfe.getList

import com.moor.shelflyfe.ui.Category
import com.moor.shelflyfe.ui.Section
import io.objectbox.android.ObjectBoxLiveData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ExploreViewModel(var repository: BookRepository) : ViewModel() {

    var sections= MutableLiveData<List<Section>>()
    //var genre = MutableLiveData<Genre>()
    private  var trendingBox= ObjectBox.boxStore.boxFor(Trending::class.java)
    init {
        viewModelScope.launch {
            repository.getBestSellers()
            val bestSellers= repository.getBestSellers()
            val fiction= bestSellers?.lists?.getList("combined-print-and-e-book-fiction")
            val nonfiction=bestSellers?.lists?.getList("combined-print-and-e-book-nonfiction")
            val advice= bestSellers?.lists?.getList("advice-how-to-and-miscellaneous")
            val business= bestSellers?.lists?.getList("business-books")
            val youngAdult= bestSellers?.lists?.getList("young-adult-hardcover")

            sections.value = listOf(
                Section("Fiction",fiction!!),
                Section("Non Fiction",nonfiction!!),
                Section("Advice",advice!!),
                Section("Business",business!!),
                Section("Young Adult",youngAdult!!)
            )
        }
    }

    val featured= ObjectBoxLiveData(trendingBox.query().build())

    var bestSellerList= liveData {
        emit(repository.getBestSellerList().results)
    }
    var trendingCategories= liveData {
        val categories= repository.getTopBooks(size = 50)
            .entry
            ?.asSequence()
            ?.map { e->e.category!! }
            ?.groupBy { g->g.id }
            ?.map { p->p }
            ?.sortedByDescending { p->p.value.size }
            ?.map { p->p.value.first() }
            ?.distinctBy { g->g.id }
            ?.map { g->Genre(g.id!!,Html.fromHtml(g.label).toString(), emptyList<Genre>()) }
            ?.toList()
        emit(categories)

    }


}
