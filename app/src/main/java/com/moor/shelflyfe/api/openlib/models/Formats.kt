package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class Formats(@SerializedName("pdf")
                   val pdf: Pdf,
                   @SerializedName("epub")
                   val epub: Epub,
                   @SerializedName("text")
                   val text: Text)