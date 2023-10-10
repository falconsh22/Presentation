package com.shahin.presentation.di

import com.shahin.presentation.data.sources.remote.artists.services.ArtistsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideArtistsApi(retrofit: Retrofit): ArtistsApi {
        return retrofit.create(ArtistsApi::class.java)
    }
}
