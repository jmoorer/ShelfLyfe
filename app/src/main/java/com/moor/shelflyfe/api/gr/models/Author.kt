package com.moor.shelflyfe.api.gr.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "author")
class Author2 {

    @PropertyElement
    var id:String?=null

    @PropertyElement
    var name:String?=null


    @PropertyElement(name="image_url")
    var imageUrl:String?=null


    @PropertyElement(name="small_image_url")
    var smallImageUrl:String?=null


    @PropertyElement
    var about:String?=null

    @PropertyElement
    var gender:String?=null


    @Element
    var books:Books?=null
}

@Xml
class Books{
    @Element
    var book:List<BookData>?=null
}