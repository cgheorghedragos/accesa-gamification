package com.ciurezu.gheorghe.dragos.clientmobile.di

import com.ciurezu.gheorghe.dragos.clientmobile.presentation.LoginScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginFragmentScreenModule {
    @ContributesAndroidInjector
    abstract fun contributeLoginFragmentScreenModule(): LoginScreenFragment
}