package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class SearchInfo(@SerializedName("textSnippet")
                      val textSnippet: String = "")