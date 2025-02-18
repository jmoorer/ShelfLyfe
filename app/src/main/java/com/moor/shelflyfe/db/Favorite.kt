package com.moor.shelflyfe.db

import android.media.Rating
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Favorite(
    @Id var id: Long = 0,
    var title:String="",
    var isbn13:String="",
    var imageUrl:String="",
    var author:String="",
    var rating: Float= 0F
)
