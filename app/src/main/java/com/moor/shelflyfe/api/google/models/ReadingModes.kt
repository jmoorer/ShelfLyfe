package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class ReadingModes(@SerializedName("image")
                        val image: Boolean = false,
                        @SerializedName("text")
                        val text: Boolean = false)