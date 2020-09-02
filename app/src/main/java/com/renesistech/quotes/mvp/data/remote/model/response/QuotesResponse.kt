package com.renesistech.quotes.mvp.data.remote.model.response

import com.google.gson.annotations.SerializedName
import com.renesistech.quotes.mvp.data.remote.model.response.Quote

data class QuotesResponse(
    @SerializedName("quotes")
    val quotes: List<Quote>
)