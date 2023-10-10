package com.shahin.presentation.di

import android.content.Context
import androidx.room.Room
import com.shahin.presentation.data.sources.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, "artists.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideArtistsDao(appDatabase: AppDatabase) = appDatabase.artistDao()

    @Provides
    fun provideArtistsRemoteKeyDao(appDatabase: AppDatabase) = appDatabase.artistRemoteKeyDao()
}
