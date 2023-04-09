package com.ciurezu.gheorghe.dragos.clientmobile.di

import android.app.Application
import android.content.Context
import com.ciurezu.gheorghe.dragos.clientmobile.network.GamificationApi
import com.ciurezu.gheorghe.dragos.clientmobile.network.RemoteDataSource
import com.ciurezu.gheorghe.dragos.clientmobile.repository.GamificationRepository
import com.ciurezu.gheorghe.dragos.clientmobile.repository.GamificationRepositoryImpl
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (private val application: Application){
    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun provideContext(): Context = application.applicationContext

    @Singleton
    @Provides
    fun providesGson() = Gson()

    @Singleton
    @Provides
    fun providesGamificationApi(remoteDataSource: RemoteDataSource, context: Context): GamificationApi {
        return remoteDataSource.buildApi(GamificationApi::class.java, context)
    }

    @Singleton
    @Provides
    fun providesGreenLightRepository(
        gamificationApi: GamificationApi
    ): GamificationRepository {
        return GamificationRepositoryImpl(gamificationApi)
    }
}