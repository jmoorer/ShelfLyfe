package com.moor.shelflyfe.api.openlib.models.subject

import com.google.gson.annotations.SerializedName

data class SubjectResponse(@SerializedName("works")
                           val works: List<WorksItem>?,
                           @SerializedName("subject_type")
                           val subjectType: String = "",
                           @SerializedName("languages")
                           val languages: List<LanguagesItem>?,
                           @SerializedName("subjects")
                           val subjects: List<SubjectsItem>?,
                           @SerializedName("people")
                           val people: List<PeopleItem>?,
                           @SerializedName("places")
                           val places: List<PlacesItem>?,
                           @SerializedName("times")
                           val times: List<TimesItem>?,
                           @SerializedName("work_count")
                           val workCount: Int = 0,
                           @SerializedName("name")
                           val name: String = "",
                           @SerializedName("publishers")
                           val publishers: List<PublishersItem>?,
                           @SerializedName("ebook_count")
                           val ebookCount: Int = 0,
                           @SerializedName("key")
                           val key: String = "",
                           @SerializedName("authors")
                           val authors: List<AuthorsItem>?)