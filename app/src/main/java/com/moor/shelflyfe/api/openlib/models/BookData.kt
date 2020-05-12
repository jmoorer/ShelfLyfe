package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class BookData(@SerializedName("subject_places")
                val subjectPlaces: List<SubjectPlacesItem>?,
                    @SerializedName("subject_people")
                val subjectPeople: List<SubjectPeopleItem>?,
                    @SerializedName("identifiers")
                val identifiers: Identifiers,
                    @SerializedName("subjects")
                val subjects: List<SubjectsItem>?,
                    @SerializedName("title")
                val title: String = "",
                    @SerializedName("url")
                val url: String = "",
                    @SerializedName("cover")
                val cover: Cover,
                    @SerializedName("publish_places")
                val publishPlaces: List<PublishPlacesItem>?,
                    @SerializedName("ebooks")
                val ebooks: List<EbooksItem>?,
                    @SerializedName("publishers")
                val publishers: List<PublishersItem>?,
                    @SerializedName("links")
                val links: List<LinksItem>?,
                    @SerializedName("publish_date")
                val publishDate: String = "",
                    @SerializedName("key")
                val key: String = "",
                    @SerializedName("authors")
                val authors: List<AuthorsItem>?)