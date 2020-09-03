package com.renesistech.quotes.mvp.data.remote.model

import com.google.gson.annotations.SerializedName
import com.renesistech.quotes.mvp.data.remote.model.response.Quote

data class BaseModel(
    @SerializedName("statusCode")
    var statusCode: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("totalPages")
    var totalPages: Int,
    @SerializedName("currentPage")
    var currentPage: String,
    @SerializedName("quotes")
    var quotes: List<Quote>
)