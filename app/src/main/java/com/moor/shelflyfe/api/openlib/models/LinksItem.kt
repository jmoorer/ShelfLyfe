package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class LinksItem(@SerializedName("title")
                     val title: String = "",
                     @SerializedName("url")
                     val url: String = "")