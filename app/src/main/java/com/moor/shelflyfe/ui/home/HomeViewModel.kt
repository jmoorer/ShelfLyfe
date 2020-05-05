package com.moor.shelflyfe.ui.home

import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.nyt.models.OverviewResult
import com.moor.shelflyfe.asBook
import com.moor.shelflyfe.ui.Section


class HomeViewModel(var repository: BookRepository) : ViewModel() {

    private val bestSellersLists = MediatorLiveData<OverviewResult>()
    private val popularBooks  = MediatorLiveData<Section>()

    init {
        //loadData()

    }


    val featured= liveData {
        emit(repository.getBestSellers()!!.lists!!.flatMap { l-> l.bestSellers!! }
            .filter { b->b.rank==1 }
            .distinctBy { b->b.primaryIsbn13 }
            .map { b->b.asBook()}
        )
    }

    val popularBookList= liveData{
        emit(
            Section(
                "Popular Ebooks",
                repository.getTopBooks()!!.map { b -> b.asBook() })
        )
        emit(
            Section(
                "Popular Audiobooks",
                repository.getTopAudioBooks()!!.map { b -> b.asBook() })
        )
    }

    fun getPopularList(): LiveData<Section> {
        return  popularBooks
    }



    fun loadData(){
//        bestSellersLists.addSource(repository.getBestSellers()){books->
//            bestSellersLists.postValue(books)
//        }
//        popularBooks.addSource(repository.getTopBooks()){books->
//            popularBooks.postValue(Section("Popular Ebooks",books.map {b->b.asBook()}))
//        }
//
//        popularBooks.addSource(repository.getTopAudioBooks()){books->
//            popularBooks.postValue(Section("Popular Audiobooks",books.map {b->b.asBook() }))
//        }
//        popularBooks.addSource(repository.getBestSellerList("e-book-fiction")){
//            it.bestSellers?.let { books->
//                popularBooks.postValue(Section("Fiction",books.map {b->b.asBook()}))
//            }
//        }
//        popularBooks.addSource(repository.getBestSellerList("e-book-nonfiction")){
//            it.bestSellers?.let { books->
//                popularBooks.postValue(Section("Non Fiction",books.map { b->b.asBook()}))
//            }
//        }
    }


}
