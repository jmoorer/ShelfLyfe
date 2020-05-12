package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class Cover(@SerializedName("small")
                 val small: String = "",
                 @SerializedName("large")
                 val large: String = "",
                 @SerializedName("medium")
                 val medium: String = "")