package com.moor.shelflyfe.api.openlib.models.subject

import com.google.gson.annotations.SerializedName

data class PublishersItem(@SerializedName("count")
                          val count: Int = 0,
                          @SerializedName("name")
                          val name: String = "",
                          @SerializedName("key")
                          val key: String = "")