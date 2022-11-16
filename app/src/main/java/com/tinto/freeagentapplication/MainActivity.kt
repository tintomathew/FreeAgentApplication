/*
 * *
 *  * Created by tinto on 16/11/2022, 11:09
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 08:33
 *
 */

package com.tinto.freeagentapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.tinto.freeagentapplication.databinding.ActivityMainBinding
import com.tinto.freeagentapplication.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.isLoading.postValue(true)
    }
}