package com.renesistech.quotes.mvp.view.splash

import com.renesistech.quotes.mvp.data.remote.model.BaseModel
import com.renesistech.quotes.mvp.data.remote.model.response.QuotesResponse
import com.renesistech.quotes.mvp.view.base.BaseInterfaces

interface SplashInterfaces {

    interface SplashView : BaseInterfaces.BaseView {
        fun showData(data: BaseModel<QuotesResponse>)
        fun showError(error: String)
    }

    interface SplashPresenter<T> {
        fun getAllQuotes(page: Int, limit: Int)
    }
}