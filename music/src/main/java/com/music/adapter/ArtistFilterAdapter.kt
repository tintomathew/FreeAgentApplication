package com.music.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.music.databinding.ItemArtistFilterBinding
import com.music.listener.PlaylistFilter

class ArtistFilterAdapter(
    private var itemList: ArrayList<String>,
    private val listener: PlaylistFilter
) :
    RecyclerView.Adapter<ArtistFilterAdapter.ArtistViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemArtistFilterBinding = ItemArtistFilterBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return ArtistViewHolder(itemBinding, listener)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return itemList.size
    }

    // Holds the views for adding the artists
    class ArtistViewHolder(
        private var binding: ItemArtistFilterBinding,
        private var listener: PlaylistFilter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(artist: String) {
            binding.listener = listener
            binding.name = artist
            binding.executePendingBindings()
        }
    }
}