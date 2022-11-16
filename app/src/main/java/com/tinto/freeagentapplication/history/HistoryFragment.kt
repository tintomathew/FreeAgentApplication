/*
 * *
 *  * Created by tinto on 16/11/2022, 11:08
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 10:39
 *
 */

package com.tinto.freeagentapplication.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.tinto.freeagentapplication.util.Extensions.isNetWorkConnected
import com.tinto.freeagentapplication.R
import com.tinto.freeagentapplication.adapter.HistoryAdapter
import com.tinto.freeagentapplication.databinding.FragmentHistoryBinding
import com.tinto.freeagentapplication.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var binding: FragmentHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_history, container, false
        )
        val view: View = binding.getRoot()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetup()
    }

    /* setting the observers and making the network call to get the data */
    private fun initialSetup() {
        if (this.context?.isNetWorkConnected() == true) {
            viewModel.getCurrencyRateByDate()
        } else {
            viewModel.isLoading.postValue(false)
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            populateData()
            binding.isLoading = it
        }

        viewModel.error.observe(viewLifecycleOwner) {
            if (it) {
                binding.error.visibility = View.VISIBLE
            } else {
                binding.error.visibility = View.GONE
            }
        }
    }

    /* set recycler view adapter for the cake list */
    private fun populateData() {
        binding.currency.text = viewModel.selectedCurrency
        historyAdapter = HistoryAdapter(viewModel.historyList)
        binding.recyclerView.adapter = historyAdapter
    }
}