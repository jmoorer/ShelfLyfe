package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class AccessInfo(@SerializedName("accessViewStatus")
                      val accessViewStatus: String = "",
                      @SerializedName("country")
                      val country: String = "",
                      @SerializedName("viewability")
                      val viewability: String = "",
                      @SerializedName("pdf")
                      val pdf: Pdf,
                      @SerializedName("webReaderLink")
                      val webReaderLink: String = "",
                      @SerializedName("epub")
                      val epub: Epub,
                      @SerializedName("publicDomain")
                      val publicDomain: Boolean = false,
                      @SerializedName("quoteSharingAllowed")
                      val quoteSharingAllowed: Boolean = false,
                      @SerializedName("embeddable")
                      val embeddable: Boolean = false,
                      @SerializedName("textToSpeechPermission")
                      val textToSpeechPermission: String = "")