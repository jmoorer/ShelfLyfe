package com.moor.shelflyfe.api.openlib.models.subject

import com.google.gson.annotations.SerializedName

data class LanguagesItem(@SerializedName("count")
                         val count: Int = 0,
                         @SerializedName("name")
                         val name: String = "")