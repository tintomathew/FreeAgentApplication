/*
 * *
 *  * Created by Tinto Mathew on 24/11/2022, 16:16
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 24/11/2022, 16:16
 *
 */

package com.music.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.music.databinding.ItemPlaylistBinding
import com.music.model.Entry
import java.util.Locale

class PlaylistAdapter(private var itemList: ArrayList<Entry>) :
    RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>(), Filterable {

    private var songFilterList = ArrayList<Entry>()
    private var mainList = ArrayList<Entry>()

    init {
        songFilterList = itemList
        mainList = itemList
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    val filterResults = FilterResults()
                    filterResults.values = mainList
                    return filterResults
                } else {
                    val resultList = ArrayList<Entry>()
                    for (row in itemList) {
                        if (row.name.label.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    songFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = songFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                itemList = results?.values as ArrayList<Entry>
                notifyDataSetChanged()
            }
        }
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemPlaylistBinding = ItemPlaylistBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return PlaylistViewHolder(itemBinding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return itemList.size
    }

    // Holds the views for adding the Playlist
    class PlaylistViewHolder(
        private var binding: ItemPlaylistBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(entry: Entry) {
            binding.item = entry
            binding.executePendingBindings()
        }
    }
}