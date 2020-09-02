package com.renesistech.quotes.di.component

import android.app.Application
import com.renesistech.quotes.di.module.ActivityBuildersModule
import com.renesistech.quotes.di.module.AppModule
import com.renesistech.quotes.di.module.FragmentBuildersModule
import com.renesistech.quotes.di.module.PresenterBuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class,
        FragmentBuildersModule::class,
        PresenterBuildersModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(igiApp: Application)


}