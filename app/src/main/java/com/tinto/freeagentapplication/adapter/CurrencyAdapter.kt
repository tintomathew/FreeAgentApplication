/*
 * *
 *  * Created by tinto on 16/11/2022, 11:06
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 10:39
 *
 */

package com.tinto.freeagentapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinto.freeagentapplication.databinding.ItemCurrencyBinding
import com.tinto.freeagentapplication.listeners.CurrencyItemClick
import com.tinto.freeagentapplication.model.CurrencyModel


class CurrencyAdapter(
    private val itemList: List<CurrencyModel>,
    private val currencyItemClick: CurrencyItemClick
) :
    RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemCurrencyBinding = ItemCurrencyBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return CurrencyViewHolder(itemBinding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(itemList[position], currencyItemClick = currencyItemClick, position)
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return itemList.size
    }

    // Holds the views for adding the CurrencyModel
    class CurrencyViewHolder(
        private var binding: ItemCurrencyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            currencyModel: CurrencyModel, currencyItemClick: CurrencyItemClick,
            position: Int
        ) {
            binding.currencyModel = currencyModel
            binding.clickHandler = currencyItemClick
            binding.position = position
            binding.executePendingBindings()
        }
    }
}