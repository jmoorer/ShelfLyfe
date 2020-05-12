package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class Details(@SerializedName("pagination")
                   val pagination: String = "",
                   @SerializedName("notes")
                   val notes: String = "",
                   @SerializedName("works")
                   val works: List<WorksItem>?,
                   @SerializedName("number_of_pages")
                   val numberOfPages: Int = 0,
                   @SerializedName("description")
                   val description: String = "",
                   @SerializedName("isbn_10")
                   val isbn10: List<String>?,
                   @SerializedName("title")
                   val title: String = "",
                   @SerializedName("type")
                   val type: Type,
                   @SerializedName("isbn_13")
                   val isbn: List<String>?,
                   @SerializedName("publish_places")
                   val publishPlaces: List<String>?,
                   @SerializedName("lccn")
                   val lccn: List<String>?,
                   @SerializedName("publish_country")
                   val publishCountry: String = "",
                   @SerializedName("by_statement")
                   val byStatement: String = "",
                   @SerializedName("publishers")
                   val publishers: List<String>?,
                   @SerializedName("dewey_decimal_class")
                   val deweyDecimalClass: List<String>?,
                   @SerializedName("physical_format")
                   val physicalFormat: String = "",
                   @SerializedName("lc_classifications")
                   val lcClassifications: List<String>?,
                   @SerializedName("last_modified")
                   val lastModified: LastModified,
                   @SerializedName("key")
                   val key: String = "",
                   @SerializedName("covers")
                   val covers: List<Int>?,
                   @SerializedName("ocaid")
                   val ocaid: String = "",
                   @SerializedName("languages")
                   val languages: List<LanguagesItem>?,
                   @SerializedName("local_id")
                   val localId: List<String>?,
                   @SerializedName("created")
                   val created: Created,
                   @SerializedName("identifiers")
                   val identifiers: Identifiers,
                   @SerializedName("subjects")
                   val subjects: List<String>?,
                   @SerializedName("source_records")
                   val sourceRecords: List<String>?,
                   @SerializedName("weight")
                   val weight: String = "",
                   @SerializedName("oclc_numbers")
                   val oclcNumbers: List<String>?,
                   @SerializedName("revision")
                   val revision: Int = 0,
                   @SerializedName("contributors")
                   val contributors: List<ContributorsItem>?,
                   @SerializedName("table_of_contents")
                   val tableOfContents: List<TableOfContentsItem>?,
                   @SerializedName("publish_date")
                   val publishDate: String = "",
                   @SerializedName("latest_revision")
                   val latestRevision: Int = 0,
                   @SerializedName("physical_dimensions")
                   val physicalDimensions: String = "",
                   @SerializedName("authors")
                   val authors: List<AuthorsItem>?)