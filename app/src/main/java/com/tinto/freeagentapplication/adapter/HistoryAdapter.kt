/*
 * *
 *  * Created by tinto on 16/11/2022, 11:07
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 10:39
 *
 */

package com.tinto.freeagentapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tinto.freeagentapplication.databinding.ItemDateBasedBinding
import com.tinto.freeagentapplication.model.HistoryRateModel


class HistoryAdapter(private val itemList: List<HistoryRateModel>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemDateBasedBinding = ItemDateBasedBinding.inflate(
            layoutInflater,
            parent,
            false
        )
        return HistoryViewHolder(itemBinding)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return itemList.size
    }

    // Holds the views for adding the HistoryRateModel
    class HistoryViewHolder(
        private var binding: ItemDateBasedBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(historyRateModel: HistoryRateModel) {
            binding.historyRateModel = historyRateModel
            binding.executePendingBindings()
        }
    }
}