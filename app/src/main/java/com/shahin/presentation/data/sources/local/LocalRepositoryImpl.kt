package com.shahin.presentation.data.sources.local

import androidx.paging.PagingSource
import com.shahin.presentation.data.models.local.ArtistEntity
import com.shahin.presentation.data.models.local.RemoteKey
import com.shahin.presentation.data.models.remote.artists.Artist
import com.shahin.presentation.data.sources.local.db.artists.ArtistsDao
import com.shahin.presentation.data.sources.local.db.artists.ArtistsRemoteKeyDao
import com.shahin.presentation.extensions.toArtistEntity
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(
    private val artistsDao: ArtistsDao,
    private val remoteKeyDao: ArtistsRemoteKeyDao
): LocalRepository {
    override suspend fun insertArtists(artists: List<Artist>, query: String) {
        return artistsDao.insertArtists(artists.map { it.toArtistEntity(query) })
    }

    override suspend fun insertArtist(artist: Artist, query: String) {
        return artistsDao.insertArtist(artist.toArtistEntity(query))
    }

    override suspend fun deleteArtist(artist: Artist) {
        return artistsDao.deleteArtist(artist.toArtistEntity())
    }

    override fun getArtistsFlow(query: String): PagingSource<Int, ArtistEntity> {
        return artistsDao.getArtistsFlow(query)
    }

    override suspend fun insertOrReplaceRemoteKey(remoteKey: RemoteKey) {
        return remoteKeyDao.insertOrReplace(remoteKey)
    }

    override suspend fun remoteKeyByQuery(query: String): RemoteKey? {
        return remoteKeyDao.remoteKeyByQuery(query)
    }

    override suspend fun remoteKeyById(id: Int): RemoteKey? {
        return remoteKeyDao.remoteKeyById(id)
    }

    override suspend fun clearRemoteKeys() {
        return remoteKeyDao.clearRemoteKeys()
    }

}
