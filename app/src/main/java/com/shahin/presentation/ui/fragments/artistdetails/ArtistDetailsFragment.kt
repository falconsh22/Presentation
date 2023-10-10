package com.shahin.presentation.ui.fragments.artistdetails

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.OneShotPreDrawListener
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.shahin.presentation.R
import com.shahin.presentation.databinding.FragmentArtistDetailsBinding
import com.shahin.presentation.extensions.setMaterialSharedElementTransition
import com.shahin.presentation.ui.fragments.BaseFragment
import com.shahin.presentation.ui.fragments.artistdetails.adapters.SongsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ArtistDetailsFragment: BaseFragment<FragmentArtistDetailsBinding>(FragmentArtistDetailsBinding::inflate) {

    private val viewModel: ArtistDetailsViewModel by viewModels()

    private val args: ArtistDetailsFragmentArgs by navArgs()

    private val songsAdapter = SongsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setMaterialSharedElementTransition()
        postponeEnterTransition()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, true) {
            navigateBack()
        }

        Glide.with(requireContext())
            .asBitmap()
            .load(args.artistView?.artist?.pictureMedium)
            .error(R.drawable.ic_launcher_background)
            .into(binding.artisImg)

        setupUi()

        OneShotPreDrawListener.add(binding.cardview) {
            startPostponedEnterTransition()
        }
    }

    private fun setupUi() {
        ViewCompat.setTransitionName(binding.container, args.artistView?.transitionName)
        binding.artistNameTxt.text = args.artistView?.artist?.name
        binding.songsList.adapter = songsAdapter
        songsAdapter.submitList(viewModel.dummyData)
    }

    private fun navigateBack() {
        findNavController().popBackStack()
    }
}
