package com.moor.shelflyfe.api.google.models

import com.google.gson.annotations.SerializedName

data class SaleInfo(@SerializedName("country")
                    val country: String = "",
                    @SerializedName("isEbook")
                    val isEbook: Boolean = false,
                    @SerializedName("saleability")
                    val saleability: String = "")