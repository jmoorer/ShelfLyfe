package com.moor.shelflyfe.api.itunes.models

import com.google.gson.annotations.SerializedName

data class Feed(@SerializedName("country")
                val country: String = "",
                @SerializedName("copyright")
                val copyright: String = "",
                @SerializedName("author")
                val author: Author,
                @SerializedName("icon")
                val icon: String = "",
                @SerializedName("links")
                val links: List<LinksItem>?,
                @SerializedName("id")
                val id: String = "",
                @SerializedName("title")
                val title: String = "",
                @SerializedName("updated")
                val updated: String = "",
                @SerializedName("results")
                val results: List<ResultsItem>?)