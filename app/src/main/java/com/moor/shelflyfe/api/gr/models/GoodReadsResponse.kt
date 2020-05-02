package com.moor.shelflyfe.api.gr.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml


@Xml
class BookResponse{

    @Element
    var book: Book?= null
}


@Xml
class AuthorResponse{

    @Element
    var author: Author?= null
}


