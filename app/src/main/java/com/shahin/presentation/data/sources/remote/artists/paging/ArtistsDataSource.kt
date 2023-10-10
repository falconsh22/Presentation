package com.shahin.presentation.data.sources.remote.artists.paging

import com.shahin.presentation.data.models.remote.RemoteResponse
import com.shahin.presentation.data.models.remote.artists.Artist
import com.shahin.presentation.network.NetworkResponse

interface ArtistsDataSource {
    suspend fun searchArtists(query: String, page : Int, pageLimit: Int) : NetworkResponse<RemoteResponse<Artist>>
}
