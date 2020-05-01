package com.moor.shelflyfe.api.nyt.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BuyLink {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

}