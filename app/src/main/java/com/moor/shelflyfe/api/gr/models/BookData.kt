package com.moor.shelflyfe.api.gr.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml


@Xml
class BookData {

    @PropertyElement
    var id:String?=null


    @PropertyElement
    var title:String?=null


    @PropertyElement
    var isbn13:String?=null


    @PropertyElement(name = "country_code")
    var countryCode:String?=null

    @PropertyElement(name="image_url")
    var imageUrl:String?=null

    @PropertyElement(name="publication_year")
    var publicationYear:String?=null

    @PropertyElement(name="publication_month")
    var publicationMonth:String?=null

    @PropertyElement(name="publication_day")
    var publicationDay:String?=null

    @PropertyElement
    var publisher:String?=null


    @PropertyElement(name="is_ebook")
    var isEBook:Boolean?=null

    @PropertyElement
    var description:String?=null

    @PropertyElement(name="average_rating")
    var averageRating:String?=null

    @Element
    var authors :Authors? = null

}

//@Xml
//class  Authors{
//    @Element
//    var author :Author? = null
//}
