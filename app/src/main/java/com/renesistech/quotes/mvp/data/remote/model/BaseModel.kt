package com.renesistech.quotes.mvp.data.remote.model

import com.google.gson.annotations.SerializedName

data class BaseModel<T>(
    @SerializedName("statusCode")
    var statusCode: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("totalPages")
    var totalPages: Int,
    @SerializedName("data")
    var data: T? = null
)