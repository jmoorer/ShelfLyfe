package com.moor.shelflyfe.api.nyt.models

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class SellerListInfo {
    @SerializedName("list_name")
    @Expose
    var listName: String? = null

    @SerializedName("display_name")
    @Expose
    var displayName: String? = null

    @SerializedName("list_name_encoded")
    @Expose
    var listNameEncoded: String? = null

    @SerializedName("oldest_published_date")
    @Expose
    var oldestPublishedDate: String? = null

    @SerializedName("newest_published_date")
    @Expose
    var newestPublishedDate: String? = null

    @SerializedName("updated")
    @Expose
    var updated: String? = null

}