package com.moor.shelflyfe.ui


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class Book(val title:String, val author:String, val isbn:String?, val imageUrl: String?):Parcelable

data class Category(var id:String, var name:String,var iconResoucre:Int?=null)

data class Section(var title:String, var books:List<Book>)


data class BookDetails(
    var isbn:String,
    var title: String,
    val imageUrl: String?,
    val similarBooks: List<Book> = emptyList(),
    val description:String,
    var author: List<String>,
    var rating :Float,
    var publisher:String?,
    var publishedDate: String?
)

data class Author(val id:String?="",var name:String,var imageUrl: String?,val rating: Float)

