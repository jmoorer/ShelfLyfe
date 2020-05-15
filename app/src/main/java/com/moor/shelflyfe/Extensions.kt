package com.moor.shelflyfe

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.AlertDialog
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import com.moor.shelflyfe.api.google.models.ItemsItem
import com.moor.shelflyfe.api.itunes.models.Entry
import com.moor.shelflyfe.api.itunes.models.ResultsItem
import com.moor.shelflyfe.api.nyt.models.BestSeller
import com.moor.shelflyfe.api.nyt.models.SellerList
import com.moor.shelflyfe.ui.Book
import com.squareup.picasso.Picasso
import org.apache.commons.text.WordUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Matcher
import java.util.regex.Pattern


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
        this.volumeInfo.industryIdentifiers?.firstOrNull()?.identifier?:"",
        this.volumeInfo.imageLinks?.thumbnail?.replace("http", "https")
    )
}
fun String.toDisplayCase(): String {
   return WordUtils.capitalize(this,' ', '_','-').replace("-"," ")
}


fun Entry.asBook(): Book {
    var imageUrl= images?.last()?.url
    var isbn=""
    imageUrl?.let { url->
        val pattern: Pattern = Pattern.compile("\\b(?:\\d-?){13}\\b")
        val matcher: Matcher = pattern.matcher(url)
        if(matcher.find())
            isbn=matcher.group()
    }
    return Book(title!!,author!!,isbn,imageUrl)
}

fun List<SellerList>.getList(key:String): List<Book>? {
    return this.first{it.listNameEncoded==key}.bestSellers?.map { it.asBook() }
}

fun View.animateView(
    toVisibility: Int,
    toAlpha: Float,
    duration: Int
) {
    val show = toVisibility == View.VISIBLE
    if (show) {
        this.alpha = 0f
    }
    this.visibility = View.VISIBLE

    this.animate()
        .setDuration(duration.toLong())
        .alpha(if (show) toAlpha else 0F)
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
              this@animateView.visibility = toVisibility
            }
        })
}
fun Fragment.creatLoadingDialog()= AlertDialog.Builder(context)
    .setView(R.layout.loading_fragment)
    .setCancelable(false)
    .create()