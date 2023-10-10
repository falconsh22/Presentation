package com.shahin.presentation.di

import com.google.gson.GsonBuilder
import com.shahin.presentation.BuildConfig
import com.shahin.presentation.network.HttpInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        httpInterceptor: HttpInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(httpInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    /**
     * making sure log interceptor only runs on debug mode - preventing production compromise
     */
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        return logging
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}
