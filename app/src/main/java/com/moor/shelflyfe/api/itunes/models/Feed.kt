package com.moor.shelflyfe.api.itunes.models


import com.tickaroo.tikxml.annotation.*

@Xml
class Feed {
    @Element
    var entry:List<Entry>?=null;
}

@Xml
class Entry{

    @PropertyElement(name = "im:name")
    var title:String?=null

    @PropertyElement
    var summary:String?=null

   @Element(name = "im:image")
    var images:List<Image>?=null

    @PropertyElement(name = "im:artist")
    var author:String?=null

    @Element(name = "category")
    var category: Category?=null
}

@Xml
class Image{
    @Attribute
    var height: Int?=null

    @TextContent
    var url:String?=null;
}

@Xml
class Category{
    @Attribute(name = "im:id")
    var id:String?=null

    @Attribute(name = "label")
    var label:String?=null
}