package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class PanelizationSummary(@SerializedName("containsImageBubbles")
                               val containsImageBubbles: Boolean = false,
                               @SerializedName("containsEpubBubbles")
                               val containsEpubBubbles: Boolean = false)