package com.shahin.presentation.data.sources.local.db.artists

import androidx.paging.DataSource
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.shahin.presentation.data.models.local.ArtistEntity

@Dao
interface ArtistsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: ArtistEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtists(artists: List<ArtistEntity>)

    @Delete
    suspend fun deleteArtist(artist: ArtistEntity)

    @Query("SELECT * FROM artists")
    fun getArtists(): List<ArtistEntity>

    @Transaction
    @Query("SELECT * FROM artists  WHERE `query` LIKE :query")
    fun getArtistsFlow(query: String): PagingSource<Int, ArtistEntity>

}
