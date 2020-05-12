package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class EbooksItem(@SerializedName("formats")
                      val formats: Formats,
                      @SerializedName("preview_url")
                      val previewUrl: String = "",
                      @SerializedName("read_url")
                      val readUrl: String = "",
                      @SerializedName("availability")
                      val availability: String = "")