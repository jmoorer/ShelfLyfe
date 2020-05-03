package com.moor.shelflyfe

import android.widget.ImageView
import androidx.palette.graphics.Palette
import com.moor.shelflyfe.api.itunes.models.ResultsItem
import com.moor.shelflyfe.ui.home.Book
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import com.moor.shelflyfe.api.nyt.models.BestSeller
import com.moor.shelflyfe.api.nyt.models.ListResult

fun<T> Call<T>.makeCall(callback: (Throwable?, Response<T>?) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) = callback(null, response)
        override fun onFailure(call: Call<T>, t: Throwable) = callback(t, null)
    })
}

fun ImageView.load(url:String,callback: ((Exception?, Palette?) -> Unit)?=null){
    Picasso.get().load(url).fit().into(this,object :com.squareup.picasso.Callback{
        override fun onSuccess() {
            callback?.let { extractPallete(url, it) }
        }
        override fun onError(e: Exception?) {

        }
    })
}

fun BestSeller.asBook():Book{
    return Book(this.title!!,this.author!!,this.bookImage!!)
}
fun ResultsItem.asBook():Book{
    return Book(this.name,this.artistName,this.artworkUrl)
}