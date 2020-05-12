package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class BookDeatil(@SerializedName("preview")
                val preview: String = "",
                      @SerializedName("preview_url")
                val previewUrl: String = "",
                      @SerializedName("bib_key")
                val bibKey: String = "",
                      @SerializedName("details")
                val details: Details,
                      @SerializedName("thumbnail_url")
                val thumbnailUrl: String = "",
                      @SerializedName("info_url")
                val infoUrl: String = "")