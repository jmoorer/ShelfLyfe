package com.moor.shelflyfe.ui.home

import androidx.lifecycle.*
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.nyt.models.OverviewResult
import com.moor.shelflyfe.api.nyt.models.SellerListInfo


class HomeViewModel(var repository: BookRepository) : ViewModel() {

    private val bestSellersLists = MediatorLiveData<OverviewResult>()
    private val popularBooks  = MediatorLiveData<Section>()

    init {
        loadData()
    }

    fun getFeatured(): LiveData<List<Book>>{
        return Transformations.map(bestSellersLists){ov->
            ov.lists!!
               // ?.filter{l->l.listNameEncoded!!.contains("combined")}
                .flatMap { l-> l.books!! }
                //.sortedBy { b->b.rank }
                .filter { b->b.rank==1 }
                .distinctBy { b->b.primaryIsbn13 }
                .map { b->Book(b.title!!,b.author!!,b.bookImage!!)}

        }
    }

    fun getPopularList(): LiveData<Section> {

        return  popularBooks
    }



    fun loadData(){
        bestSellersLists.addSource(repository.getBestSellers()){books->
            bestSellersLists.postValue(books)
        }
        popularBooks.addSource(repository.getTopBooks()){books->
            popularBooks.postValue(Section("Popular Ebooks",books.map { Book(it.name,it.artistName,it.artworkUrl) }))
        }
        popularBooks.addSource(repository.getTopAudioBooks()){books->
            popularBooks.postValue(Section("Popular Audiobooks",books.map { Book(it.name,it.artistName,it.artworkUrl) }))
        }
        popularBooks.addSource(repository.getBestSellerList("e-book-fiction")){
            it.books?.let {books->
                popularBooks.postValue(Section("Fiction",books.map { b->Book(b.title!!,b.author!!,b.bookImage!!)}))
            }
        }
        popularBooks.addSource(repository.getBestSellerList("e-book-nonfiction")){
            it.books?.let { books->
                popularBooks.postValue(Section("Non Fiction",books.map { b->Book(b.title!!,b.author!!,b.bookImage!!)}))
            }
        }
    }
}
