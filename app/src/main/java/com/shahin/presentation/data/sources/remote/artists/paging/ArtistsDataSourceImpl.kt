package com.shahin.presentation.data.sources.remote.artists.paging

import com.shahin.presentation.data.models.remote.RemoteResponse
import com.shahin.presentation.data.models.remote.artists.Artist
import com.shahin.presentation.data.sources.remote.artists.services.ArtistsApi
import com.shahin.presentation.network.NetworkResponse
import com.shahin.presentation.network.NetworkResponseWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArtistsDataSourceImpl @Inject constructor(
    private val artistsApi: ArtistsApi,
    private val dispatcher: CoroutineDispatcher
): NetworkResponseWrapper(), ArtistsDataSource {

    override suspend fun searchArtists(
        query: String,
        page: Int,
        pageLimit: Int,
    ): NetworkResponse<RemoteResponse<Artist>> {
        return withContext(dispatcher) {
            networkResponseOf {
                artistsApi.searchArtists(
                    artistName = query,
                    page = page,
                    limit = pageLimit
                )
            }
        }
    }

}
