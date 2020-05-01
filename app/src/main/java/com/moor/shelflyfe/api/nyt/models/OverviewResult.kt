package com.moor.shelflyfe.api.nyt.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.moor.shelflyfe.api.nyt.models.SellerList

class OverviewResult {
    @SerializedName("bestsellers_date")
    @Expose
    var bestsellersDate: String? = null

    @SerializedName("published_date")
    @Expose
    var publishedDate: String? = null

    @SerializedName("published_date_description")
    @Expose
    var publishedDateDescription: String? = null

    @SerializedName("previous_published_date")
    @Expose
    var previousPublishedDate: String? = null

    @SerializedName("next_published_date")
    @Expose
    var nextPublishedDate: String? = null

    @SerializedName("lists")
    @Expose
    var lists: List<SellerList>? = null

}