package com.renesistech.quotes.mvp.data.remote.model.request

import com.google.gson.annotations.SerializedName

data class QuoteRequest (
    @SerializedName("page")
    var page: Int,
    @SerializedName("limit")
    var limit: Int
)