package com.shahin.presentation.data.sources.remote

import androidx.paging.PagingData
import com.shahin.presentation.data.models.remote.artists.Artist
import kotlinx.coroutines.flow.Flow

interface RemoteRepository {
    suspend fun searchArtists(artistName: String, onError: (String?) -> Unit): Flow<PagingData<Artist>>
}
