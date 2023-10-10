package com.shahin.presentation.data.sources.remote.artists.services

import com.shahin.presentation.data.models.remote.OrderType
import com.shahin.presentation.data.models.remote.RemoteResponse
import com.shahin.presentation.data.models.remote.artists.Artist
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistsApi {

    @GET("search/artist")suspend fun searchArtists(
        @Query("q") artistName: String,
        @Query("order") order: String = OrderType.RANKING.name,
        @Query("index") page: Int,
        @Query("limit") limit: Int = 25
    ): Response<RemoteResponse<Artist>>

}
