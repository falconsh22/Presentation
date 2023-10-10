package com.shahin.presentation.data.sources.remote.artists

import androidx.paging.PagingData
import com.shahin.presentation.data.models.remote.artists.Artist
import kotlinx.coroutines.flow.Flow

interface ArtistsRepository {
    suspend fun searchArtists(query: String, onError: (String?) -> Unit): Flow<PagingData<Artist>>
}
