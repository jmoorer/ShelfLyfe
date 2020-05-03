package com.moor.shelflyfe.api.nyt.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BestSeller {
    @SerializedName("age_group")
    @Expose
    var ageGroup: String? = null

    @SerializedName("amazon_product_url")
    @Expose
    var amazonProductUrl: String? = null

    @SerializedName("article_chapter_link")
    @Expose
    var articleChapterLink: String? = null

    @SerializedName("author")
    @Expose
    var author: String? = null

    @SerializedName("book_image")
    @Expose
    var bookImage: String? = null

    @SerializedName("book_image_width")
    @Expose
    var bookImageWidth: Int? = null

    @SerializedName("book_image_height")
    @Expose
    var bookImageHeight: Int? = null

    @SerializedName("book_review_link")
    @Expose
    var bookReviewLink: String? = null

    @SerializedName("contributor")
    @Expose
    var contributor: String? = null

    @SerializedName("contributor_note")
    @Expose
    var contributorNote: String? = null

    @SerializedName("created_date")
    @Expose
    var createdDate: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("first_chapter_link")
    @Expose
    var firstChapterLink: String? = null

    @SerializedName("price")
    @Expose
    var price: Int? = null

    @SerializedName("primary_isbn10")
    @Expose
    var primaryIsbn10: String? = null

    @SerializedName("primary_isbn13")
    @Expose
    var primaryIsbn13: String? = null

    @SerializedName("book_uri")
    @Expose
    var bookUri: String? = null

    @SerializedName("publisher")
    @Expose
    var publisher: String? = null

    @SerializedName("rank")
    @Expose
    var rank: Int? = null

    @SerializedName("rank_last_week")
    @Expose
    var rankLastWeek: Int? = null

    @SerializedName("sunday_review_link")
    @Expose
    var sundayReviewLink: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("updated_date")
    @Expose
    var updatedDate: String? = null

    @SerializedName("weeks_on_list")
    @Expose
    var weeksOnList: Int? = null

    @SerializedName("buy_links")
    @Expose
    var buyLinks: List<BuyLink>? = null

}