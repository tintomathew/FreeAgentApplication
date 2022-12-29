/*
 * *
 *  * Created by Tinto Mathew on 24/11/2022, 21:16
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 24/11/2022, 21:16
 *
 */

package com.music.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {

    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined

}