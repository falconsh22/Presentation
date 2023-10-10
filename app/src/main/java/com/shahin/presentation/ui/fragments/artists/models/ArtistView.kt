package com.shahin.presentation.ui.fragments.artists.models

import android.os.Parcelable
import com.shahin.presentation.data.models.remote.artists.Artist
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistView(
    val transitionName: String,
    val artist: Artist
) : Parcelable
