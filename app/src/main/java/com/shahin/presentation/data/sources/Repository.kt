package com.shahin.presentation.data.sources

import androidx.paging.PagingData
import com.shahin.presentation.data.models.remote.artists.Artist
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun searchArtists(artistName: String, onError: (String?) -> Unit): Flow<PagingData<Artist>>
}
