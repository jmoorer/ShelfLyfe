package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class SubjectPlacesItem(@SerializedName("name")
                             val name: String = "",
                             @SerializedName("url")
                             val url: String = "")