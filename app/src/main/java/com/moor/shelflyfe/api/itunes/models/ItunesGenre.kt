package com.moor.shelflyfe.api.itunes.models

import com.google.gson.annotations.SerializedName

data class ItunesGenre(@SerializedName("name")
                 val name: String = "",
                 @SerializedName("id")
                 val id: String = "",
                 @SerializedName("subgenres")
                 val subgenres:Map<String,ItunesGenre>?=null
)

