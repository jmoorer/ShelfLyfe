package com.moor.shelflyfe.api.gr.models

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml


@Xml
class GoodreadsResponse{

    @Element
    var book: Book?= null
}


