package com.moor.shelflyfe.db

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
@Entity
data class Download(
    @Id var id: Long = 0,
    var title:String="",
    var isbn13:String="",
    var image:ByteArray?=null,
    var author:String="",
    var path: String= "",
    var lastLocation:String=""
)

