package com.shahin.presentation.data.sources.local

import androidx.paging.PagingSource
import com.shahin.presentation.data.models.local.ArtistEntity
import com.shahin.presentation.data.models.local.RemoteKey
import com.shahin.presentation.data.models.remote.artists.Artist

interface LocalRepository {
    suspend fun insertArtists(artists: List<Artist>, query: String)
    suspend fun insertArtist(artist: Artist, query: String)
    suspend fun deleteArtist(artist: Artist)

    fun getArtistsFlow(query: String): PagingSource<Int, ArtistEntity>

    suspend fun insertOrReplaceRemoteKey(remoteKey: RemoteKey)
    suspend fun remoteKeyByQuery(query: String): RemoteKey?
    suspend fun remoteKeyById(id: Int): RemoteKey?
    suspend fun clearRemoteKeys()
}
