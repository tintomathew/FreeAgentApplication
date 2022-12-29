/*
 * *
 *  * Created by tinto on 17/12/2022, 22:35
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 17/12/2022, 22:15
 *
 */

package com.tinto.freeagentapplication.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.findNavController
import com.core.di.Navigator
import com.tinto.freeagentapplication.MainActivity
import com.tinto.freeagentapplication.util.Extensions.isNetWorkConnected
import com.tinto.freeagentapplication.R
import com.tinto.freeagentapplication.adapter.CurrencyAdapter
import com.tinto.freeagentapplication.databinding.FragmentHomeBinding
import com.tinto.freeagentapplication.listeners.CurrencyItemClick
import com.tinto.freeagentapplication.data.repo.model.CurrencyModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), CurrencyItemClick, AdapterView.OnItemSelectedListener {
    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var binding: FragmentHomeBinding
    // This dependency could be satisfied by the injector (Not implemented in that example)
   // private lateinit var navigatorProvider: Navigator.Provider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        val view: View = binding.getRoot()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialSetup()
        setUpSpinner()
     //   configureObservables()
    }

//    private fun configureObservables() {
//        observe(viewModel.navigateTo) { screenEvent ->
//            screenEvent.getContentIfNotHandled()?.let { screen ->
//                //navigatorProvider.get(screen).navigate(this.requireActivity())
//            }
//        }
//    }

    /* setting the observers and making the network call to get the data */
    private fun initialSetup() {
        if (this.context?.isNetWorkConnected() == true) {
            viewModel.getCurrency("EUR")
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

        binding.historyButton.setOnClickListener(View.OnClickListener {
            if (viewModel.getFilterInput().isEmpty()) {
                Toast.makeText(this.context, getString(R.string.select_currency), Toast.LENGTH_LONG)
                    .show()
            } else {
//                findNavController().navigate(
//                    HomeFragmentDirections.navigateToHistory()
//                )

                val filterInput = viewModel.getFilterInput()
                val request = NavDeepLinkRequest.Builder
                    .fromUri("android-app://example.google.app/music_home/${filterInput}".toUri())
                    .build()
                findNavController().navigate(request)
            }
        })
    }

    /* Create an ArrayAdapter using the string array and a default spinner layout */
    private fun setUpSpinner() {
        this.context?.let {
            ArrayAdapter.createFromResource(
                it,
                R.array.currency_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                binding.currentSelection.adapter = adapter
                binding.currentSelection.setOnItemSelectedListener(this);
            }
        }
    }

    /* set recycler view adapter for the currency list */
    private fun populateData() {
        currencyAdapter = CurrencyAdapter(viewModel.responseData, this)
        binding.recyclerView.adapter = currencyAdapter
    }

    override fun onCurrencyRateClick(currencyModel: CurrencyModel, position: Int) {
        viewModel.responseData[position].isSelected = !viewModel.responseData[position].isSelected
        currencyAdapter.notifyItemChanged(position)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (!viewModel.selectedCurrency.equals(
                binding.currentSelection.adapter.getItem(position).toString()
            )
        ) {
            viewModel.getCurrency(binding.currentSelection.adapter.getItem(position).toString())
        }
        viewModel.selectedCurrency = binding.currentSelection.adapter.getItem(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
    companion object {

        fun launch(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }
}

object GoToHomeScreen : Navigator {

    override fun navigate(activity: Activity) { HomeFragment.launch(activity) }
}

