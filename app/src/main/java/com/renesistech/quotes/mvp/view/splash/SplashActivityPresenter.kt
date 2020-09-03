package com.renesistech.quotes.mvp.view.splash

import com.renesistech.quotes.Application
import com.renesistech.quotes.di.module.SchedulerProvider
import com.renesistech.quotes.helper.Constant
import com.renesistech.quotes.helper.DeviceAvailabilityRetryWithDelay
import com.renesistech.quotes.helper.ResponseCode
import com.renesistech.quotes.mvp.data.DataManager
import com.renesistech.quotes.mvp.data.local.DatabaseHelper
import com.renesistech.quotes.mvp.view.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.SocketTimeoutException
import java.util.*
import javax.inject.Inject

open class SplashActivityPresenter<V : SplashInterfaces.SplashView> @Inject
constructor(
    val mDataManager: DataManager,
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable
) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    SplashInterfaces.SplashPresenter<Any?> {

    override fun getAllQuotes(page: Int, limit: Int) {

        var databaseHelper = DatabaseHelper(Application.applicationContext())

        mvpView!!.showLoading()

        compositeDisposable.add(
            mDataManager.getAllQuotes(page, limit)
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(
                    DeviceAvailabilityRetryWithDelay(
                        Constant.MAX_RETRIES,
                        Constant.RETRY_DELAY
                    )
                )
                .subscribeOn(Schedulers.io())
                .subscribe({
                    if (ResponseCode.isBetweenSuccessRange(it.statusCode)) {
                        mvpView!!.hideLoading()
                        mvpView!!.showData(it)

                        it.let { ti ->
                            ti.quotes.let { data ->
                                for (item in data) {
                                    databaseHelper.insertQuote(item.id, item.quoteText, item.quoteAuthor)
                                }
                                mDataManager.mPreferencesHelper.putString("date", Calendar.getInstance().time.toString())
                            }
                        }

                    } else {
                        mvpView!!.hideLoading()
                        mvpView!!.showError(it.message)
                    }
                }, {
                    if (it is SocketTimeoutException) {
                        mvpView!!.hideLoading()
                        mvpView!!.showError("Sorry our server is currently not available temporarily. Please try again in few minutes")
                    } else {
                        mvpView!!.hideLoading()
                        mvpView!!.showError(it.message.toString())
                    }
                })
        )
    }
}
