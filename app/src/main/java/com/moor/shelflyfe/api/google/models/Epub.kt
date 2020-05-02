package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class Epub(@SerializedName("isAvailable")
                val isAvailable: Boolean = false)