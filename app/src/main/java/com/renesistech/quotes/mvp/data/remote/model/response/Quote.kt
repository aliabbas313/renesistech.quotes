package com.renesistech.quotes.mvp.data.remote.model.response

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("_id")
    val id: String,
    @SerializedName("quoteAuthor")
    val quoteAuthor: String,
    @SerializedName("quoteText")
    val quoteText: String
)