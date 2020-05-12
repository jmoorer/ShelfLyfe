package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class LastModified(@SerializedName("type")
                        val type: String = "",
                        @SerializedName("value")
                        val value: String = "")