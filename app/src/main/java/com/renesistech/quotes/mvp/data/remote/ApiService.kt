package com.renesistech.quotes.mvp.data.remote

import com.renesistech.quotes.BuildConfig
import com.renesistech.quotes.mvp.data.remote.model.BaseModel
import com.renesistech.quotes.mvp.data.remote.model.response.QuotesResponse
import io.reactivex.Observable
import retrofit2.http.*

private const val BASE_URL = BuildConfig.URL_BASE + "/api"

interface ApiService {

    @GET("$BASE_URL/v2/quotes")
    fun getAllQuotes(
        @Query("page") page: Int,
        @Query("limit") limit: Int): Observable<BaseModel<QuotesResponse>>

}
