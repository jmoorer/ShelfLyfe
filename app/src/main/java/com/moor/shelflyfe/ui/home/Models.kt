package com.moor.shelflyfe.ui.home




data class Book(val title:String,val author:String,val imageUrl:String)

data class Category(var name:String,var iconResoucre:Int?)

data class Section(var title:String, var books:List<Book>)