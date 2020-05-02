package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class ImageLinks(@SerializedName("thumbnail")
                      val thumbnail: String = "",
                      @SerializedName("smallThumbnail")
                      val smallThumbnail: String = "")