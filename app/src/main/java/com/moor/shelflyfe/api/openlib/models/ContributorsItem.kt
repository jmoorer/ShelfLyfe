package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class ContributorsItem(@SerializedName("role")
                            val role: String = "",
                            @SerializedName("name")
                            val name: String = "")