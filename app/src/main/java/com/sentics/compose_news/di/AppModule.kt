package com.sentics.compose_news.di

import android.app.Application
import com.sentics.compose_news.data.user.LocalUserManagerImpl
import com.sentics.compose_news.domain.user.LocalUserManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)
}
