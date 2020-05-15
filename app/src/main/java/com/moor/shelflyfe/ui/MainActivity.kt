package com.moor.shelflyfe.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.codekidlabs.storagechooser.StorageChooser
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.moor.shelflyfe.R
import com.moor.shelflyfe.api.BookRepository
import com.moor.shelflyfe.ui.explore.ExploreViewModel
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.core.parameter.parametersOf


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        findViewById<BottomNavigationView>(R.id.bottomNav).apply {
            setupWithNavController(navController)
        }

    }
}
