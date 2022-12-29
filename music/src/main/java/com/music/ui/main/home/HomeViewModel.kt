/*
 * *
 *  * Created by Tinto Mathew on 25/11/2022, 13:07
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 25/11/2022, 12:30
 *
 */

package com.music.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.music.model.Entry
import com.music.model.PlaylistData
import com.music.repository.PlayListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currencyRepository: PlayListRepository
) : ViewModel() {

    // hold the play list data
    var responseData: MutableLiveData<PlaylistData> = MutableLiveData()

    // hold the progress indicator status
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    // hold the progress indicator status
    var showFilterDialog: MutableLiveData<Boolean> = MutableLiveData()

    var filteredList: MutableLiveData<ArrayList<Entry>> = MutableLiveData()

    init {
        isLoading.value = true
        showFilterDialog.value = false
    }

    /**
     * Method containing a API call to fetch the play list.
     *
     */
    fun getData() {
        viewModelScope.launch {
            isLoading.postValue(true)
            currencyRepository.getPlayList().data?.let {
                responseData.postValue(it)
                filteredList.value = it.feed.entry as ArrayList<Entry>
            }
            isLoading.postValue(false)
        }
    }

    /**
     * Method containing the filter dialog live data
     *
     */
    fun showFilterDialog() {
        showFilterDialog.value = true
    }

    /**
     * Method to get the unique artists
     *
     * @param inputItem Array of Entry items
     *
     * @return list of Artist names
     */
    fun getArtistList(inputItem: List<Entry>?): ArrayList<String> {
        val list:ArrayList<String> = ArrayList()
        inputItem?.distinctBy {
            it.artist.label
        }?.map {
            list.add(it.artist.label)
        }
        return list
    }

    /**
     * Method to filter the list based on selected artist
     *
     * @param inputItem Array of Entry items
     * @param name artist name to filter
     *
     * @return filtered list of Entry item
     *
     */
    fun getArtistFilteredData(inputItem: List<Entry>?, name: String): ArrayList<Entry> {
        return inputItem?.filter {
            it.artist.label == name
        } as ArrayList<Entry>
    }

    /**
     * Method to clear all the filters
     *
     */
    fun clearFilter() {
        filteredList.value = responseData.value?.feed?.entry as ArrayList<Entry>
    }
}