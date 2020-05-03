package com.moor.shelflyfe.api.itunes.models

import com.google.gson.annotations.SerializedName

data class GenresItem(@SerializedName("genreId")
                      val genreId: String = "",
                      @SerializedName("name")
                      val name: String = "",
                      @SerializedName("url")
                      val url: String = "")