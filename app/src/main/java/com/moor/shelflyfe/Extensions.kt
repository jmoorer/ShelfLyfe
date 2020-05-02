package com.moor.shelflyfe

import android.widget.ImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun<T> Call<T>.makeCall(callback: (Throwable?, Response<T>?) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) = callback(null, response)
        override fun onFailure(call: Call<T>, t: Throwable) = callback(t, null)
    })
}

fun ImageView.load(url:String){
    Picasso.get().load(url).fit().into(this)
}