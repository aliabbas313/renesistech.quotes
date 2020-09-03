package com.renesistech.quotes.mvp.view.splash

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.renesistech.quotes.R
import com.renesistech.quotes.helper.Helper
import com.renesistech.quotes.mvp.data.local.PreferencesHelper
import com.renesistech.quotes.mvp.data.remote.model.BaseModel
import com.renesistech.quotes.mvp.view.main.MainActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class SplashActivity : AppCompatActivity(), SplashInterfaces.SplashView {

    fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidInjector

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mMainPresenter: SplashActivityPresenter<SplashInterfaces.SplashView>

    @Inject
    lateinit var preferencesHelper: PreferencesHelper

    private val mHideHandler = Handler()
    private val mShowPart2Runnable = Runnable {
        // Delayed display of UI elements
        val actionBar = this@SplashActivity.supportActionBar
        actionBar?.show()
    }
    private val mHideRunnable = { hideActionBar() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        hideActionBar()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
        mMainPresenter.onAttach(this)

        if(!preferencesHelper.checkObject("date")) {
            val rnds = (0..7810).random()
            mMainPresenter.getAllQuotes(rnds, 10)
        } else {
            val c = Calendar.getInstance()
            val sdf = SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
            val currentDate: Date = sdf.parse(c.time.toString())

            val date = preferencesHelper.getString("date")
//            val date = "Wed Sep 02 04:41:36 GMT+05:00 2020"
            val spf = SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy")
            val cacheDate = spf.parse(date)

            if(Helper.remainingDays(cacheDate, currentDate)!!.toInt() >= 1) {
                val rnds = (0..7810).random()
                mMainPresenter.getAllQuotes(rnds, 10)
            } else {
                Helper.makeDelay(this, MainActivity::class, 500)
            }
        }
    }

    open fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {

        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    override fun showData(apiResponse: BaseModel) {
        Helper.makeDelay(this, MainActivity::class, 500)
    }

    override fun showError(error: String) {
        Helper.makeDelay(this, MainActivity::class, 500)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun hideActionBar() {
        // Hide UI first
        val actionBar = supportActionBar
        actionBar?.hide()

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable)
    }

    override fun delayedHide(delayMillis: Int) {
        mHideHandler.removeCallbacks(mHideRunnable)
        mHideHandler.postDelayed(mHideRunnable, delayMillis.toLong())
    }
}