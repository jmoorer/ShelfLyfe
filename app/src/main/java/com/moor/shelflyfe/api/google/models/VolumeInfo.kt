package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class VolumeInfo(@SerializedName("industryIdentifiers")
                      val industryIdentifiers: List<IndustryIdentifiersItem>?,
                      @SerializedName("pageCount")
                      val pageCount: Int = 0,
                      @SerializedName("printType")
                      val printType: String = "",
                      @SerializedName("readingModes")
                      val readingModes: ReadingModes,
                      @SerializedName("previewLink")
                      val previewLink: String = "",
                      @SerializedName("canonicalVolumeLink")
                      val canonicalVolumeLink: String = "",
                      @SerializedName("description")
                      val description: String = "",
                      @SerializedName("language")
                      val language: String = "",
                      @SerializedName("title")
                      val title: String = "",
                      @SerializedName("imageLinks")
                      val imageLinks: ImageLinks?=null,
                      @SerializedName("subtitle")
                      val subtitle: String = "",
                      @SerializedName("panelizationSummary")
                      val panelizationSummary: PanelizationSummary,
                      @SerializedName("publishedDate")
                      val publishedDate: String = "",
                      @SerializedName("publisher")
                      val publisher: String = "",
                      @SerializedName("averageRating")
                      var averageRating:Float?=null,
                      @SerializedName("maturityRating")
                      val maturityRating: String = "",
                      @SerializedName("allowAnonLogging")
                      val allowAnonLogging: Boolean = false,
                      @SerializedName("contentVersion")
                      val contentVersion: String = "",
                      @SerializedName("authors")
                      val authors: List<String>?,
                      @SerializedName("infoLink")
                      val infoLink: String = "")