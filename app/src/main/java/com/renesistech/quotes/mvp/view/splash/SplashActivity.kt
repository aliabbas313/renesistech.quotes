package com.renesistech.quotes.mvp.view.splash

import androidx.appcompat.app.AppCompatActivity
import com.renesistech.quotes.mvp.view.splash.SplashInterfaces.SplashPresenter
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.renesistech.quotes.R
import com.renesistech.quotes.mvp.data.remote.model.BaseModel
import com.renesistech.quotes.mvp.data.remote.model.response.QuotesResponse
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashInterfaces.SplashView {

    fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mMainPresenter: SplashActivityPresenter<SplashInterfaces.SplashView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mMainPresenter.onAttach(this)

        mMainPresenter.getAllQuotes(1, 10)
    }

    override fun showData(data: BaseModel<QuotesResponse>) {

    }

    override fun showError(error: String) {

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }
}