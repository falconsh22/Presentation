package com.shahin.presentation.data.sources.remote

import androidx.paging.PagingData
import com.shahin.presentation.data.models.remote.artists.Artist
import com.shahin.presentation.data.sources.remote.artists.ArtistsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val artistsRepository: ArtistsRepository
) : RemoteRepository {

    override suspend fun searchArtists(artistName: String, onError: (String?) -> Unit): Flow<PagingData<Artist>> {
        return artistsRepository.searchArtists(artistName, onError)
    }

}
