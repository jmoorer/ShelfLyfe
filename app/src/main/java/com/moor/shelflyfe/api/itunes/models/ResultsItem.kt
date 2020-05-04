package com.moor.shelflyfe.api.itunes.models

import com.google.gson.annotations.SerializedName

data class ResultsItem(@SerializedName("artworkUrl100")
                       val artworkUrl: String = "",
                       @SerializedName("releaseDate")
                       val releaseDate: String = "",
                       @SerializedName("kind")
                       val kind: String = "",
                       @SerializedName("artistUrl")
                       val artistUrl: String = "",
                       @SerializedName("genres")
                       val genres: List<GenresItem>?,
                       @SerializedName("name")
                       val name: String = "",
                       @SerializedName("artistName")
                       val artistName: String = "",
                       @SerializedName("artistId")
                       val artistId: String = "",
                       @SerializedName("id")
                       val id: String = "",
                       @SerializedName("url")
                       val url: String = "")