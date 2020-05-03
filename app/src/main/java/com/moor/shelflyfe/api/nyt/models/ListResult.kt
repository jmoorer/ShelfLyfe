package com.moor.shelflyfe.api.nyt.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class ListResult {
    @SerializedName("list_name")
    @Expose
    var listName: String? = null

    @SerializedName("list_name_encoded")
    @Expose
    var listNameEncoded: String? = null

    @SerializedName("bestsellers_date")
    @Expose
    var bestsellersDate: String? = null

    @SerializedName("published_date")
    @Expose
    var publishedDate: String? = null

    @SerializedName("published_date_description")
    @Expose
    var publishedDateDescription: String? = null

    @SerializedName("next_published_date")
    @Expose
    var nextPublishedDate: String? = null

    @SerializedName("previous_published_date")
    @Expose
    var previousPublishedDate: String? = null

    @SerializedName("display_name")
    @Expose
    var displayName: String? = null

    @SerializedName("normal_list_ends_at")
    @Expose
    var normalListEndsAt: Int? = null

    @SerializedName("updated")
    @Expose
    var updated: String? = null

    @SerializedName("books")
    @Expose
    var bestSellers: List<BestSeller>? = null

    @SerializedName("corrections")
    @Expose
    var corrections: List<Any>? = null

}