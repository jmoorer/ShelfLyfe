package com.moor.shelflyfe.db


import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class Genre(
    @Id(assignable = true) var id: Long = 0,
    val name:String,
    val key:String
)