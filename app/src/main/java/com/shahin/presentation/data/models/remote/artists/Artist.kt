package com.shahin.presentation.data.models.remote.artists

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Artist(

    @SerializedName("picture_xl")
    val pictureXl: String? = null,

    @SerializedName("tracklist")
    val tracklist: String? = null,

    @SerializedName("link")
    val link: String? = null,

    @SerializedName("picture_small")
    val pictureSmall: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("nb_album")
    val nbAlbum: Int? = null,

    @SerializedName("picture")
    val picture: String? = null,

    @SerializedName("nb_fan")
    val nbFan: Int? = null,

    @SerializedName("radio")
    val radio: Boolean? = null,

    @SerializedName("picture_big")
    val pictureBig: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("picture_medium")
    val pictureMedium: String? = null
) : Parcelable
