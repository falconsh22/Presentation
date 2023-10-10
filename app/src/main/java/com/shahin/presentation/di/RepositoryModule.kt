package com.shahin.presentation.di

import com.shahin.presentation.data.sources.Repository
import com.shahin.presentation.data.sources.RepositoryImpl
import com.shahin.presentation.data.sources.local.LocalRepository
import com.shahin.presentation.data.sources.local.LocalRepositoryImpl
import com.shahin.presentation.data.sources.remote.RemoteRepository
import com.shahin.presentation.data.sources.remote.RemoteRepositoryImpl
import com.shahin.presentation.data.sources.remote.artists.ArtistsRepository
import com.shahin.presentation.data.sources.remote.artists.ArtistsRepositoryImpl
import com.shahin.presentation.data.sources.remote.artists.paging.ArtistsDataSource
import com.shahin.presentation.data.sources.remote.artists.paging.ArtistsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindRepository(repositoryImpl: RepositoryImpl): Repository

    @Singleton
    @Binds
    abstract fun bindRemoteRepository(remoteRepositoryImpl: RemoteRepositoryImpl): RemoteRepository

    @Singleton
    @Binds
    abstract fun bindArtistsRepository(artistsRepositoryImpl: ArtistsRepositoryImpl): ArtistsRepository

    @Singleton
    @Binds
    abstract fun bindArtistsPagingDataSource(pagingDataSourceImpl: ArtistsDataSourceImpl): ArtistsDataSource

    @Singleton
    @Binds
    abstract fun bindLocalRepository(localRepositoryImpl: LocalRepositoryImpl): LocalRepository

}
