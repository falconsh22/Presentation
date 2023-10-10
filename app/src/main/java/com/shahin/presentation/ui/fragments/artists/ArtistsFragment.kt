package com.shahin.presentation.ui.fragments.artists

import android.os.Bundle
import android.view.View
import androidx.core.view.OneShotPreDrawListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.shahin.presentation.databinding.FragmentArtistsBinding
import com.shahin.presentation.extensions.onTextChanges
import com.shahin.presentation.extensions.visibleOrGone
import com.shahin.presentation.extensions.visibleOrHide
import com.shahin.presentation.ui.fragments.BaseFragment
import com.shahin.presentation.ui.fragments.artists.adapters.ArtistsAdapter
import com.shahin.presentation.ui.fragments.artists.models.ArtistView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ArtistsFragment: BaseFragment<FragmentArtistsBinding>(FragmentArtistsBinding::inflate) {

    private val viewModel: ArtistsViewModel by viewModels()

    private val artistsAdapter = ArtistsAdapter(
        onItemClicked = {
            navigateToArtistDetails(it.first, it.second)
        }
    )

    private var searchJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        setupUi()
    }

    @OptIn(FlowPreview::class)
    private fun setupUi() {
        binding.artistsList.adapter = artistsAdapter
        OneShotPreDrawListener.add(binding.root) {
            startPostponedEnterTransition()
        }
        binding.searchView
            .onTextChanges()
            .filterNotNull()
            .onEach {
                binding.loading.visibleOrHide(it.isNotEmpty())
            }
            .debounce(500)
            .filter { it.isNotEmpty() }
            .onEach {
                searchJob?.cancel()
                searchJob = lifecycleScope.launch {
                    viewModel.searchArtists(
                        artistName = it.trim(),
                        onError = {
                            if (artistsAdapter.itemCount == 0) {
                                binding.errorTxt.text = it
                                binding.errorTxt.visibleOrGone(true)
                            }
                        }
                    ).collectLatest { pagingData ->
                        if (isAdded) {
                            binding.emptyView.visibleOrHide(false)
                            binding.loading.visibleOrHide(false)
                            binding.errorTxt.visibleOrGone(false)
                            artistsAdapter.submitData(pagingData)
                        }
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
        lifecycleScope.launch {
            artistsAdapter.loadStateFlow.collectLatest { loadStates ->
                if (loadStates.append is LoadState.Error) {
                    artistsAdapter.retry()
                }
            }
        }
    }

    private fun navigateToArtistDetails(view: View, artistView: ArtistView) {
        val extras = FragmentNavigatorExtras(view to view.transitionName)
        findNavController().navigate(
            directions = ArtistsFragmentDirections.actionArtistsFragmentToArtistDetailsFragment(artistView),
            navigatorExtras = extras
        )
    }
}
