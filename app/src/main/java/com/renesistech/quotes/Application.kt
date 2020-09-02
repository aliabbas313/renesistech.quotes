package com.renesistech.quotes

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.renesistech.quotes.di.injection.AppInjector
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector

class Application : MultiDexApplication(), HasActivityInjector {

    override fun onCreate() {
        instance = this
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppInjector.init(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        TODO("Not yet implemented")
    }

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