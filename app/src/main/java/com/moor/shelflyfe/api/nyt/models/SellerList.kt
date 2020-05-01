package com.moor.shelflyfe.api.nyt.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SellerList {
    @SerializedName("list_id")
    @Expose
    var listId: Int? = null

    @SerializedName("list_name")
    @Expose
    var listName: String? = null

    @SerializedName("list_name_encoded")
    @Expose
    var listNameEncoded: String? = null

    @SerializedName("display_name")
    @Expose
    var displayName: String? = null

    @SerializedName("updated")
    @Expose
    var updated: String? = null

    @SerializedName("list_image")
    @Expose
    var listImage: String? = null

    @SerializedName("list_image_width")
    @Expose
    var listImageWidth: Int? = null

    @SerializedName("list_image_height")
    @Expose
    var listImageHeight: Int? = null

    @SerializedName("books")
    @Expose
    var books: List<Book>? = null

}