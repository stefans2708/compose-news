package com.sentics.compose_news.di

import android.app.Application
import com.sentics.compose_news.data.remote.NewsApi
import com.sentics.compose_news.data.repository.NewsRepositoryImpl
import com.sentics.compose_news.data.user.LocalUserManagerImpl
import com.sentics.compose_news.domain.repository.NewsRepository
import com.sentics.compose_news.domain.user.LocalUserManager
import com.sentics.compose_news.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository =
        NewsRepositoryImpl(newsApi)
}
