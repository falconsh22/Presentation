package com.shahin.presentation.ui.fragments.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shahin.presentation.data.models.remote.artists.Artist
import com.shahin.presentation.data.sources.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val repository: Repository,
): ViewModel() {

    suspend fun searchArtists(artistName: String, onError: (String?) -> Unit): Flow<PagingData<Artist>> {
        return repository.searchArtists(artistName, onError)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
    }

}
