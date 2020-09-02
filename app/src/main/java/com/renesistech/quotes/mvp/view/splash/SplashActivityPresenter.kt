package com.renesistech.quotes.mvp.view.splash

import com.google.gson.Gson
import com.renesistech.quotes.di.module.SchedulerProvider
import com.renesistech.quotes.helper.Constant
import com.renesistech.quotes.helper.DeviceAvailabilityRetryWithDelay
import com.renesistech.quotes.helper.ResponseCode
import com.renesistech.quotes.mvp.data.DataManager
import com.renesistech.quotes.mvp.data.remote.model.request.QuoteRequest
import com.renesistech.quotes.mvp.view.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.SocketTimeoutException
import javax.inject.Inject

open class SplashActivityPresenter<V : SplashInterfaces.SplashView> @Inject
constructor(val mDataManager: DataManager, schedulerProvider: SchedulerProvider, disposable: CompositeDisposable) : BasePresenter<V>(schedulerProvider = schedulerProvider, compositeDisposable = disposable),
    SplashInterfaces.SplashPresenter<Any?> {


    override fun getAllQuotes(page: Int, limit: Int) {

        mvpView!!.showLoading()

        compositeDisposable.add(mDataManager.getAllQuotes(page, limit)
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
                        var text = Gson().toJson(ti.data)
                        mDataManager.mPreferencesHelper.putString("accountDetail", text)

//                        ti.data!!.customerID.let { ii ->
//                            mDataManager.mPreferencesHelper.putString("customer_id", ii)
//                        }

                        //orca auth token
                        mDataManager.mPreferencesHelper.putString("auth_token", "Token")
//                            ti.data!!.authToken.let { ii ->
//                                mDataManager.mPreferencesHelper.putString("auth_token", ii)
//                            }

//                        ti.data!!.emergencyContact.let { ii ->
//                            var emergencyContact = Gson().toJson(ii)
//                            mDataManager.mPreferencesHelper.putString(
//                                "emergencyContact",
//                                emergencyContact
//                            )
//                        }
                    }

                    mDataManager.mPreferencesHelper.putBoolean("isLogin", true)
                    mDataManager.mPreferencesHelper.putBoolean("isTouch", false)

//                        if (it.data!!.image != null) {
//                            mDataManager.mPreferencesHelper.putString("profile_pic", "data:image/png;base64,"+it.data?.image.toString())
//                        }
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
