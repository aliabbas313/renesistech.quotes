package com.renesistech.quotes.di.module

import com.renesistech.quotes.mvp.data.DataManager
import com.renesistech.quotes.mvp.view.splash.SplashActivityPresenter
import com.renesistech.quotes.mvp.view.splash.SplashInterfaces
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class PresenterBuildersModule {

    @Provides
    fun provideSplashActivityPresenter(dataManager: DataManager,
                                       schedulerProvider: SchedulerProvider,
                                       disposable: CompositeDisposable): SplashActivityPresenter<SplashInterfaces.SplashView> {
        return SplashActivityPresenter(dataManager, schedulerProvider, disposable)
    }

}