package com.shahin.presentation.data.sources

import androidx.paging.PagingData
import com.shahin.presentation.data.models.remote.artists.Artist
import com.shahin.presentation.data.sources.remote.RemoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteRepository: RemoteRepository
): Repository {
    override suspend fun searchArtists(artistName: String, onError: (String?) -> Unit): Flow<PagingData<Artist>> {
        return remoteRepository.searchArtists(artistName, onError)
    }
}
