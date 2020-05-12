package com.moor.shelflyfe.api.openlib.models

import com.google.gson.annotations.SerializedName

data class Identifiers(@SerializedName("amazon")
                       val amazon: List<String>?,
                       @SerializedName("goodreads")
                       val goodreads: List<String>?,
                       @SerializedName("google")
                       val google: List<String>?,
                       @SerializedName("librarything")
                       val librarything: List<String>?)