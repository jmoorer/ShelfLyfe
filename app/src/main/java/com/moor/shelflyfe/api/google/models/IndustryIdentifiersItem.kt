package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class IndustryIdentifiersItem(@SerializedName("identifier")
                                   val identifier: String = "",
                                   @SerializedName("type")
                                   val type: String = "")