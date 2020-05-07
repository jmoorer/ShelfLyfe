package com.moor.shelflyfe.ui

import java.io.Serializable


data class Book(val title:String, val author:String, val isbn:String, val imageUrl: String?)

data class Category(var name:String,var iconResoucre:Int?=null)

data class Section(var title:String, var books:List<Book>)


data class BookDetails(var title: String,val author:String)