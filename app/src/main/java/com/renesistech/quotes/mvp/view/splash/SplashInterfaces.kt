package com.renesistech.quotes.mvp.view.splash

import com.renesistech.quotes.mvp.data.remote.model.BaseModel
import com.renesistech.quotes.mvp.data.remote.model.response.QuotesResponse
import com.renesistech.quotes.mvp.view.base.BaseInterfaces

interface SplashInterfaces {

    interface SplashPresenter<T> {
        fun getAllQuotes(page: Int, limit: Int)
    }

    interface SplashView : BaseInterfaces.BaseView {
        fun hideActionBar()
        fun delayedHide(delayMillis: Int)
        fun showData(data: BaseModel)
        fun showError(error: String)
    }
}