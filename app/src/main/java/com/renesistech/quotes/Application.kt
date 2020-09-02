package com.renesistech.quotes

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.renesistech.quotes.di.injection.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class Application : MultiDexApplication(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        instance = this
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector


    companion object {
        private var instance: Application? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

        @JvmStatic
        fun getAppContext(): Application? {
            return instance
        }
    }
}