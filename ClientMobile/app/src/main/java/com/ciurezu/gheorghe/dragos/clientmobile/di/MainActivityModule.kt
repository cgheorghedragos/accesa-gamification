package com.ciurezu.gheorghe.dragos.clientmobile.di

import com.ciurezu.gheorghe.dragos.clientmobile.MainActivity
import com.ciurezu.gheorghe.dragos.clientmobile.presentation.LoginScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivityModule(): MainActivity
}