package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class GoogleResponse(@SerializedName("totalItems")
                          val totalItems: Int = 0,
                          @SerializedName("kind")
                          val kind: String = "",
                          @SerializedName("items")
                          val items: List<ItemsItem>?)