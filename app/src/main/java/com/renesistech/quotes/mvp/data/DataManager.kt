package com.renesistech.quotes.mvp.data

import com.renesistech.quotes.mvp.data.local.PreferencesHelper
import com.renesistech.quotes.mvp.data.remote.ApiService
import com.renesistech.quotes.mvp.data.remote.model.BaseModel
import com.renesistech.quotes.mvp.data.remote.model.response.QuotesResponse
import io.reactivex.Observable
import javax.inject.Inject

class DataManager
@Inject
constructor(var mPreferencesHelper: PreferencesHelper, var iApiService: ApiService) {

    var data: String? = null

    fun getAllQuotes(page: Int, limit: Int): Observable<BaseModel> {

        return iApiService.getAllQuotes(page, limit)
    }
}
