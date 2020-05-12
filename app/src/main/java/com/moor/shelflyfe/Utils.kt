package com.moor.shelflyfe

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception
import  androidx.palette.graphics.Palette
import com.moor.shelflyfe.api.itunes.ItunesService
import com.squareup.picasso.NetworkPolicy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import org.koin.android.ext.android.get


fun preloadImage(url: String){
    Picasso.get().load(url).fetch()
}

fun openLibCoverUrl(id: Int)="https://covers.openlibrary.org/b/id/${id}-M.jpg"

