package com.moor.shelflyfe.di

import com.moor.shelflyfe.BuildConfig
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.google.GoogleBooksService
import com.moor.shelflyfe.api.gr.GoodReadsService
import com.moor.shelflyfe.api.itunes.ItunesService
import com.moor.shelflyfe.api.nyt.NytService
import com.moor.shelflyfe.ui.bookdetail.BookDetailFragment
import com.moor.shelflyfe.ui.bookdetail.BookDetailViewModel
import com.moor.shelflyfe.ui.booklist.BookListViewModel
import com.moor.shelflyfe.ui.explore.ExploreViewModel
import com.moor.shelflyfe.ui.home.HomeViewModel
import com.moor.shelflyfe.ui.list.ListViewModel
import com.moor.shelflyfe.ui.search.SearchViewModel
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import kotlin.math.sin


val applicationModule = module(override = true) {

    single<NytService> {
        Retrofit.Builder()
            .baseUrl(NytService.baseUrl)
            .client(OkHttpClient.Builder().addInterceptor{ chain ->
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url()
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api-key", BuildConfig.NytApiKey)
                    .build()

                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NytService::class.java)
    }

    single<GoodReadsService>{
         Retrofit.Builder().baseUrl(GoodReadsService.baseUrl)
         .client(OkHttpClient.Builder().addInterceptor{ chain ->
             val original: Request = chain.request()
             val originalHttpUrl: HttpUrl = original.url()
             val url = originalHttpUrl.newBuilder()
                 .addQueryParameter("key", BuildConfig.GoodReadsKey)
                 .build()

             val requestBuilder: Request.Builder = original.newBuilder()
                 .url(url)
             val request: Request = requestBuilder.build()
             chain.proceed(request)
         }.build())
         .addConverterFactory(TikXmlConverterFactory.create(
             TikXml.Builder()
             .exceptionOnUnreadXml(false)
             .build()
         ))
//             .addConverterFactory(
//                 SimpleXmlConverterFactory.createNonStrict(
//                     Persister( AnnotationStrategy() // important part!
//                             )))
        .build().create(GoodReadsService::class.java)
    }

    single <GoogleBooksService>{
        Retrofit.Builder().baseUrl(GoogleBooksService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GoogleBooksService::class.java)
    }
    single<ItunesService>{
        Retrofit.Builder().baseUrl(ItunesService.basrUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ItunesService::class.java)
    }
    factory { BookRepository(get(),get(),get(),get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ExploreViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ListViewModel() }
    viewModel { BookListViewModel(get()) }
    viewModel { BookDetailViewModel(get()) }
}