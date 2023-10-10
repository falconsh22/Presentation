package com.shahin.presentation.data.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.shahin.presentation.data.models.remote.artists.Artist

@Entity(
    tableName = "artists",
    indices = [Index(value = ["record_id"])]
)
data class ArtistEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "record_id")
    val recordId: Int? = null,

    @ColumnInfo("query")
    val query: String,

    @ColumnInfo("picture_xl")
    val pictureXl: String,

    @ColumnInfo("tracklist")
    val tracklist: String,

    @ColumnInfo("link")
    val link: String,

    @ColumnInfo("picture_small")
    val pictureSmall: String,

    @ColumnInfo("type")
    val type: String,

    @ColumnInfo("nb_album")
    val nbAlbum: Int,

    @ColumnInfo("picture")
    val picture: String,

    @ColumnInfo("nb_fan")
    val nbFan: Int,

    @ColumnInfo("radio")
    val radio: Boolean,

    @ColumnInfo("picture_big")
    val pictureBig: String,

    @ColumnInfo("name")
    val name: String,

    @ColumnInfo("id")
    val id: String,

    @ColumnInfo("picture_medium")
    val pictureMedium: String
)
