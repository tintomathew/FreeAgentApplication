/*
 * *
 *  * Created by tinto on 29/12/2022, 20:08
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 29/12/2022, 20:07
 *
 */

package com.salt.coretest

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {

    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined

}

