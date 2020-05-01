package com.moor.shelflyfe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.moor.shelflyfe.api.BookRepository


import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity() {


    private val bookRepository:BookRepository by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//
        bookRepository.nytService.getListByType("hardcover-fiction").makeCall { throwable, response ->
//
//            TODO()
        }

        bookRepository.goodReadsService.getBookDetails("9780735224315").makeCall { throwable, response ->

            TODO()

        }
    }
}
