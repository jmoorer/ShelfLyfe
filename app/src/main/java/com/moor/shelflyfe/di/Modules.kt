package com.moor.shelflyfe.di


import com.folioreader.Config
import com.folioreader.FolioReader
import com.moor.shelflyfe.BuildConfig
import com.moor.shelflyfe.R
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.google.GoogleBooksService
import com.moor.shelflyfe.api.itunes.ItunesService
import com.moor.shelflyfe.api.nyt.NytService
import com.moor.shelflyfe.api.openlib.OpenLibService
import com.moor.shelflyfe.ui.bookdetail.BookDetailViewModel
import com.moor.shelflyfe.ui.booklist.BookListViewModel
import com.moor.shelflyfe.ui.explore.ExploreViewModel
import com.moor.shelflyfe.ui.home.HomeViewModel
import com.moor.shelflyfe.ui.list.ListViewModel
import com.moor.shelflyfe.ui.search.SearchViewModel
import com.moor.shelflyfe.ui.subject.SubjectViewModel
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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


    single <GoogleBooksService>{
        Retrofit.Builder().baseUrl(GoogleBooksService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GoogleBooksService::class.java)
    }

    single<ItunesService>{
        Retrofit.Builder().baseUrl(ItunesService.basrUrl)
            .addConverterFactory(TikXmlConverterFactory.create(
                TikXml.Builder()
                    .exceptionOnUnreadXml(false)
                    .build()
            ))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ItunesService::class.java)
    }
    single {
        Retrofit.Builder().baseUrl(OpenLibService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(OpenLibService::class.java)
    }

    single {
       FolioReader.get().setConfig(Config().setNightMode(true)
             .setThemeColorRes(R.color.primary), true)
    }


    factory { BookRepository(get(),get(),get(),get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { ExploreViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { ListViewModel() }
    viewModel { BookListViewModel(get()) }
    viewModel { BookDetailViewModel(get()) }
    viewModel { SubjectViewModel(get()) }



}

