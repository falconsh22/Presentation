package com.shahin.presentation.extensions

import com.shahin.presentation.data.models.local.ArtistEntity
import com.shahin.presentation.data.models.remote.artists.Artist

fun Artist.toArtistEntity(query: String = "") = ArtistEntity(
    query = query,
    pictureXl = pictureXl ?: "",
    tracklist = tracklist ?: "",
    link = link ?: "",
    pictureSmall = pictureSmall ?: "",
    type = type ?: "",
    nbAlbum = nbAlbum ?: 0,
    picture = picture ?: "",
    nbFan = nbFan ?: 0,
    radio = radio ?: false,
    pictureBig = pictureBig ?: "",
    name = name ?: "",
    id = id ?: "",
    pictureMedium = pictureMedium ?: ""
)

fun ArtistEntity.toArtist() = Artist(
    pictureXl = pictureXl,
    tracklist = tracklist,
    link = link,
    pictureSmall = pictureSmall,
    type = type,
    nbAlbum = nbAlbum,
    picture = picture,
    nbFan = nbFan,
    radio = radio,
    pictureBig = pictureBig,
    name = name,
    id = id,
    pictureMedium = pictureMedium
)
