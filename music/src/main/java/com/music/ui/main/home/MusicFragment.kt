/*
 * *
 *  * Created by tinto on 29/12/2022, 18:56
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 29/12/2022, 18:53
 *
 */

package com.music.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.music.adapter.ArtistFilterAdapter
import com.music.databinding.FilterLayoutBinding
import com.music.adapter.PlaylistAdapter
import com.music.extensions.Extensions.isNetWorkConnected
import com.music.listener.PlaylistFilter
import com.music.R
import com.music.databinding.FragmentMusicBinding

class MusicFragment : Fragment(), PlaylistFilter {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var playlistAdapter: PlaylistAdapter
    private lateinit var binding: FragmentMusicBinding
    lateinit var builder: AlertDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_music, container, false
        )
        val view: View = binding.root
        binding.viewModel = viewModel
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        populateData()
    }

    /* set recycler view adapter for the playlist */
    private fun populateData() {
        if (this.context?.isNetWorkConnected() == true) {
            viewModel.getData()
        } else {
            Toast.makeText(
                this.context,
                getString(R.string.something_went_wrong),
                Toast.LENGTH_LONG
            ).show()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.isLoading = it
        }

        viewModel.filteredList.observe(viewLifecycleOwner) { data ->
            data.let {
                playlistAdapter = PlaylistAdapter(data)
                binding.recyclerView.adapter = playlistAdapter
            }
        }

        viewModel.showFilterDialog.observe(viewLifecycleOwner) {
            if (it) {
                showFilterDialog()
            }
        }

        binding.songSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                playlistAdapter.filter.filter(newText)
                return false
            }
        })
    }

    // show the filter dialog on filter button click
    private fun showFilterDialog() {
        builder = AlertDialog.Builder(this.requireContext(), R.style.CustomAlertDialog)
            .create()

        val binding: FilterLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(this.context),
            R.layout.filter_layout,
            null,
            false
        )

        val artistFilterAdapter =
            ArtistFilterAdapter(viewModel.getArtistList(viewModel.responseData.value?.feed?.entry), this)
        binding.recyclerViewFilter.adapter = artistFilterAdapter
        artistFilterAdapter.notifyDataSetChanged()

        binding.listener = this
        builder.setView(binding.root)
        builder.setCanceledOnTouchOutside(true)
        builder.show()
    }

    override fun onFilterSelection(name: String) {
        builder.cancel()
        viewModel.filteredList.value = viewModel.getArtistFilteredData(viewModel.responseData.value?.feed?.entry, name)
        playlistAdapter.notifyDataSetChanged()
    }
}