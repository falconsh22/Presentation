package com.shahin.presentation.ui.fragments.artists.adapters

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.paging.PagingDataAdapter
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.shahin.presentation.R
import com.shahin.presentation.data.models.remote.artists.Artist
import com.shahin.presentation.databinding.ItemArtistBinding
import com.shahin.presentation.extensions.onClick
import com.shahin.presentation.extensions.setTextColorOnBackground
import com.shahin.presentation.ui.fragments.artists.models.ArtistView

class ArtistsAdapter(
    private val onItemClicked: (Pair<View, ArtistView>) -> Unit,
) : PagingDataAdapter<Artist, ArtistsAdapter.ArtistViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist): Boolean {
            return oldItem == newItem
        }
    }

    private fun glideRequestListener(onResourceReady: (Bitmap) -> Unit) = object : RequestListener<Bitmap> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Bitmap>,
            isFirstResource: Boolean,
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Bitmap,
            model: Any,
            target: Target<Bitmap>?,
            dataSource: DataSource,
            isFirstResource: Boolean,
        ): Boolean {
            onResourceReady.invoke(resource)
            return false
        }
    }

    inner class ArtistViewHolder(
        private val binding: ItemArtistBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: Artist) {
            val transitionName = binding.root.context.getString(R.string.transition_name) + "_${bindingAdapterPosition}"
            ViewCompat.setTransitionName(binding.container, transitionName)
            binding.artistNameTxt.text = artist.name
            binding.artistCardView.onClick {
                onItemClicked.invoke(
                    binding.container to ArtistView(transitionName, artist)
                )
            }
            Glide.with(binding.root)
                .asBitmap()
                .load(artist.pictureMedium)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .addListener(
                    glideRequestListener {
                        Palette.from(it).generate { palette ->
                            val vibrantColor = palette?.getDominantColor(binding.root.context.getColor(R.color.white))
                            val color = vibrantColor ?: binding.root.context.getColor(R.color.white)
                            binding.artistNameTxt.setBackgroundColor(color)
                            binding.artistNameTxt.setTextColorOnBackground(color)
                        }
                    }
                ).into(binding.artistImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        return ArtistViewHolder(
            ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

}
