package com.shahin.presentation.ui.fragments.artistdetails.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shahin.presentation.databinding.ItemSongBinding
import com.shahin.presentation.ui.fragments.artistdetails.models.Song

class SongsAdapter: ListAdapter<Song, SongsAdapter.SongViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.songName == newItem.songName
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }

    inner class SongViewHolder(
        private val binding: ItemSongBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(song: Song) {
            binding.orderTxt.text = bindingAdapterPosition.toString()
            binding.songNameTxt.text = song.songName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(
            ItemSongBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val item = getItem(position) ?: return
        holder.bind(item)
    }

    override fun submitList(list: List<Song>?) {
        super.submitList(list)
    }

}
