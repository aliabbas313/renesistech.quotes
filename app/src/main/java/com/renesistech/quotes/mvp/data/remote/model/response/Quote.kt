package com.renesistech.quotes.mvp.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("_id")
    var id: String,
    @SerializedName("quoteAuthor")
    var quoteAuthor: String,
    @SerializedName("quoteText")
    var quoteText: String
)