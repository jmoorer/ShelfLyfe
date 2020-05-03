package com.moor.shelflyfe

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception
import  androidx.palette.graphics.Palette
import com.squareup.picasso.NetworkPolicy

fun extractPallete(url:String,callback: (Exception?,Palette?) -> Unit){
    Picasso.get().load(url).into(object :Target{
        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            callback(e,null)
        }

        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
               callback(null,Palette.from(bitmap!!).generate())
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {

        }
    })
}

fun preloadImage(url: String){
    Picasso.get().load(url).fetch()
}