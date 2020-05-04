package com.moor.shelflyfe.api.itunes.models

import com.google.gson.annotations.SerializedName

data class ItunesResponse(@SerializedName("feed")
                          val feed: Feed)