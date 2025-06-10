package com.sentics.compose_news.di

import android.app.Application
import androidx.room.Room
import com.sentics.compose_news.BuildConfig
import com.sentics.compose_news.data.local.NewsDao
import com.sentics.compose_news.data.local.NewsDatabase
import com.sentics.compose_news.data.local.NewsTypeConverter
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
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideNewsApi(
        client: OkHttpClient
    ): NewsApi {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(NewsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )

    @Provides
    @Singleton
    fun provideNewsRepository(newsApi: NewsApi, newsDao: NewsDao): NewsRepository =
        NewsRepositoryImpl(newsApi, newsDao)

    @Provides
    @Singleton
    fun providesNewsDatabase(application: Application): NewsDatabase =
        Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = Constant.NEWS_DB_NAME
        )
            .addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()

    @Provides
    @Singleton
    fun provideNewsDao(database: NewsDatabase): NewsDao =
        database.newsDao
}
