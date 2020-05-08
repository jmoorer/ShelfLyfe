package com.moor.shelflyfe

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.palette.graphics.Palette
import com.moor.shelflyfe.api.google.models.ItemsItem
import com.moor.shelflyfe.api.itunes.models.ResultsItem
import com.moor.shelflyfe.api.nyt.models.BestSeller
import com.moor.shelflyfe.ui.Book
import com.squareup.picasso.Picasso
import org.apache.commons.text.WordUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun<T> Call<T>.makeCall(callback: (Throwable?, Response<T>?) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) = callback(null, response)
        override fun onFailure(call: Call<T>, t: Throwable) = callback(t, null)
    })
}

fun ImageView.load(url:String?,callback: ((Exception?, Palette?) -> Unit)?=null){
    url?.let {
        Picasso.get().load(url).fit().into(this,object :com.squareup.picasso.Callback{
            override fun onSuccess() {
                callback?.let {
                    val image= this@load.drawable as BitmapDrawable
                    callback(null,Palette.from(image.bitmap).generate())
                }
            }
            override fun onError(e: Exception?) {
                callback?.invoke(e,null)
            }
        })
    }?:run{
        this.setImageResource(R.drawable.ic_book)
    }

}

fun BestSeller.asBook(): Book {
    return Book(
        this.title!!,
        this.author!!,
        this.primaryIsbn13?:"",
        this.bookImage
    )
}
fun ResultsItem.asBook(): Book {
    return Book(
        this.name,
        this.artistName,
        "",
        this.artworkUrl
    )
}
fun ItemsItem.asBook(): Book {
    return Book(
        this.volumeInfo.title,
        this.volumeInfo.authors?.first()?:"??",
        this.volumeInfo.industryIdentifiers?.firstOrNull { it.identifier=="ISBN_13" }?.identifier?:"",
        this.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")
    )
}
fun String.toDisplayCase(): String {
   return WordUtils.capitalize(this,' ', '_','-').replace("-"," ")
}
