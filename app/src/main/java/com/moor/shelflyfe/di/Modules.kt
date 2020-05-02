package com.moor.shelflyfe.di

import com.moor.shelflyfe.BuildConfig
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.api.google.GoogleBooksService
import com.moor.shelflyfe.api.gr.GoodReadsService
import com.moor.shelflyfe.api.nyt.NytService
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.koin.dsl.module.applicationContext
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
        .build().create(GoodReadsService::class.java)
    }

    single <GoogleBooksService>{
        Retrofit.Builder().baseUrl(GoogleBooksService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GoogleBooksService::class.java)
    }
    factory { BookRepository(get(),get(),get()) }
}