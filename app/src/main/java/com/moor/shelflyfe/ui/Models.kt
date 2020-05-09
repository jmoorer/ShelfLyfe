package com.moor.shelflyfe.ui

import com.moor.shelflyfe.api.gr2.Similar_books
import java.io.Serializable


data class Book(val title:String, val author:String, val isbn:String, val imageUrl: String?)

data class Category(var id:String, var name:String,var iconResoucre:Int?=null)

data class Section(var title:String, var books:List<Book>)


data class BookDetails(
    var title: String,
    val imageUrl: String?,
    val similarBooks: List<Book>,
    val description:String,
    var author: Author,
    var rating :Float,
    var publisher:String?,
    var publishedDate: String?
)

data class Author(val id:String,var name:String,var imageUrl: String?,val rating: Float)

