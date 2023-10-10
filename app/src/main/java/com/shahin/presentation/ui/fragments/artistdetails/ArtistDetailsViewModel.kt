package com.shahin.presentation.ui.fragments.artistdetails

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.shahin.presentation.ui.fragments.artistdetails.models.Song
import javax.inject.Inject

class ArtistDetailsViewModel @Inject constructor(): ViewModel() {

    val dummyData = arrayListOf(
        Song(
            "Song 1"
        ),
        Song(
            "Song 2"
        ),
        Song(
            "Song 3"
        ),
        Song(
            "Song 4"
        ),
        Song(
            "Song 5"
        ),
        Song(
            "Song 6"
        )
    )

}
