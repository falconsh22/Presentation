package com.shahin.presentation.data.sources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shahin.presentation.data.sources.local.db.artists.ArtistsDao
import com.shahin.presentation.data.models.local.ArtistEntity
import com.shahin.presentation.data.models.local.RemoteKey
import com.shahin.presentation.data.sources.local.db.artists.ArtistsRemoteKeyDao

@Database(
    version = 1,
    entities = [
        ArtistEntity::class,
        RemoteKey::class
    ],
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artistDao(): ArtistsDao
    abstract fun artistRemoteKeyDao(): ArtistsRemoteKeyDao
}
