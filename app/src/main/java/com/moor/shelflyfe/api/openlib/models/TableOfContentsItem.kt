package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class TableOfContentsItem(@SerializedName("level")
                               val level: Int = 0,
                               @SerializedName("label")
                               val label: String = "",
                               @SerializedName("pagenum")
                               val pagenum: String = "",
                               @SerializedName("title")
                               val title: String = "")