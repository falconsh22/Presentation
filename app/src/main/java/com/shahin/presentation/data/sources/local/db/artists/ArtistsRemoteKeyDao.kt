package com.shahin.presentation.data.sources.local.db.artists

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.shahin.presentation.data.models.local.RemoteKey

@Dao
interface ArtistsRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Transaction
    @Query("SELECT * FROM remote_keys WHERE label = :query")
    suspend fun remoteKeyByQuery(query: String): RemoteKey?

    @Transaction
    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun remoteKeyById(id: Int): RemoteKey?

    @Query("DELETE FROM remote_keys")
    suspend fun clearRemoteKeys()
}
