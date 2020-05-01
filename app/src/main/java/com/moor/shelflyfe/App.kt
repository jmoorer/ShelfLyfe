package com.moor.shelflyfe

import android.app.Application
import com.moor.shelflyfe.di.applicationModule
import org.koin.android.ext.android.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin(androidContext = this@App, modules = listOf(applicationModule))

    }
}