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

    }
}
