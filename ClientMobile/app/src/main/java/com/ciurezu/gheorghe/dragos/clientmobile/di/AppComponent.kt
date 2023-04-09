package com.ciurezu.gheorghe.dragos.clientmobile.di

import com.ciurezu.gheorghe.dragos.clientmobile.GamificationApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        AppModule::class,
        MainActivityModule::class,
        LoginFragmentScreenModule::class
    ]
)

@Singleton
interface AppComponent {
    fun inject(application: GamificationApp)
}