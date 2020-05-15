package com.moor.shelflyfe.ui.home

import android.util.Log
import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.nyt.models.OverviewResult
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.db.Download
import com.moor.shelflyfe.db.Favorite
import com.moor.shelflyfe.db.ObjectBox
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.Section
import io.objectbox.android.ObjectBoxLiveData
import kotlinx.coroutines.launch


class HomeViewModel(var repository: BookRepository) : ViewModel() {

    private val bestSellersLists = MediatorLiveData<OverviewResult>()
    private val popularBooks  = MediatorLiveData<Section>()
    private val favoriteBox = ObjectBox.boxStore.boxFor(Favorite::class.java)
    private val downloadBox = ObjectBox.boxStore.boxFor(Download::class.java)

    init {

    }

//    var sections= liveData {
//        val favorites= favoriteBox.query().build().find()
//        emit(listOf(
//            Section("Favorites",favorites.map { Book(it.title,it.author,it.isbn13,it.imageUrl) })
//        ))
//    }

    var favorites = ObjectBoxLiveData(favoriteBox.query().build())

    var downloads = ObjectBoxLiveData(downloadBox.query().build())

    fun removeFavorite(id:Long){
        favoriteBox.remove(id);
    }

    fun removeDownload(id:Long){
        downloadBox.remove(id);
    }

    fun saveDownload(download: Download){
        downloadBox.put(download)
    }
    val featured= liveData {
        emit(repository.getBestSellers()!!.lists!!.flatMap { l-> l.bestSellers!! }
            .filter { b->b.rank==1 }
            .distinctBy { b->b.primaryIsbn13 }
            .map { b->b.asBook()}
        )
    }



    fun getPopularList(): LiveData<Section> {
        return  popularBooks
    }


}
