package com.shahin.presentation.data.sources.remote.artists

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.shahin.presentation.data.models.remote.artists.Artist
import com.shahin.presentation.data.sources.local.LocalRepository
import com.shahin.presentation.data.sources.local.db.artists.ArtistsDao
import com.shahin.presentation.data.sources.local.db.artists.ArtistsRemoteKeyDao
import com.shahin.presentation.data.sources.remote.artists.paging.ArtistsDataSource
import com.shahin.presentation.data.sources.remote.artists.paging.ArtistsPagingRepository
import com.shahin.presentation.extensions.toArtist
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArtistsRepositoryImpl @Inject constructor(
    private val artistsDataSource: ArtistsDataSource,
    private val localRepository: LocalRepository
): ArtistsRepository {
    override suspend fun searchArtists(query: String, onError: (String?) -> Unit): Flow<PagingData<Artist>> {
        return Pager(
            config = PagingConfig(
                pageSize = 40,
                prefetchDistance = 2,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            remoteMediator = ArtistsPagingRepository(
                artistsDataSource = artistsDataSource,
                localRepository = localRepository,
                query = query,
                onError = onError
            )
        ) {
            localRepository.getArtistsFlow(query)
        }.flow.map { pagingData ->
            pagingData.map { it.toArtist() }
        }
    }
}
