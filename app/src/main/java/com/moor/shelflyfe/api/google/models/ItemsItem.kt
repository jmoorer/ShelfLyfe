package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class ItemsItem(@SerializedName("saleInfo")
                     val saleInfo: SaleInfo,
                     @SerializedName("searchInfo")
                     val searchInfo: SearchInfo,
                     @SerializedName("kind")
                     val kind: String = "",
                     @SerializedName("volumeInfo")
                     val volumeInfo: VolumeInfo,
                     @SerializedName("etag")
                     val etag: String = "",
                     @SerializedName("id")
                     val id: String = "",
                     @SerializedName("accessInfo")
                     val accessInfo: AccessInfo,
                     @SerializedName("selfLink")
                     val selfLink: String = "")