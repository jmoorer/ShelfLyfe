package com.moor.shelflyfe.api.itunes.models

import com.google.gson.annotations.SerializedName

data class Author(@SerializedName("name")
                  val name: String = "",
                  @SerializedName("uri")
                  val uri: String = "")