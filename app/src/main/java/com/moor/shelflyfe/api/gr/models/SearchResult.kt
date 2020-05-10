package com.moor.shelflyfe.api.gr.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml(name = "GoodreadsResponse")
class SearchResult {

    @Path("search")
    @Element(name = "results")
    var results:List<BookResult>?=null

    @Path("search")
    @PropertyElement
    var query:String?=null

}


@Xml
class BookResult{

    @Path("work/best_book")
    @PropertyElement(name = "id")
    var id:String?=null

    @Path("work/best_book")
    @PropertyElement
    var title:String?=null

    @Path("work/best_book/author")
    @PropertyElement(name = "name")
    var author:String?=null
}