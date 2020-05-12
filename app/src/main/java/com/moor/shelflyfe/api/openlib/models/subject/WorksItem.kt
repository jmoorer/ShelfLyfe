package com.moor.shelflyfe.api.openlib.models.subject

import com.google.gson.annotations.SerializedName

data class WorksItem(@SerializedName("edition_count")
                     val editionCount: Int = 0,
                     @SerializedName("lending_identifier")
                     val lendingIdentifier: String = "",
                     @SerializedName("ia_collection")
                     val iaCollection: List<String>?,
                     @SerializedName("cover_edition_key")
                     val coverEditionKey: String = "",
                     @SerializedName("subject")
                     val subject: List<String>?,
                     @SerializedName("cover_id")
                     val coverId: Int = 0,
                     @SerializedName("checked_out")
                     val checkedOut: Boolean = false,
                     @SerializedName("title")
                     val title: String = "",
                     @SerializedName("public_scan")
                     val publicScan: Boolean = false,
                     @SerializedName("ia")
                     val ia: String = "",
                     @SerializedName("printdisabled")
                     val printdisabled: Boolean = false,
                     @SerializedName("has_fulltext")
                     val hasFulltext: Boolean = false,
                     @SerializedName("lending_edition")
                     val lendingEdition: String = "",
                     @SerializedName("lendinglibrary")
                     val lendinglibrary: Boolean = false,
                     @SerializedName("key")
                     val key: String = "",
                     @SerializedName("authors")
                     val authors: List<AuthorsItem>?)