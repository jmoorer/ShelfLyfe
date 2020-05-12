package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class AuthorsItem(@SerializedName("name")
                       val name: String = "",
                       @SerializedName("key")
                       val key: String = "")