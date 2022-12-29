/*
 * *
 *  * Created by tinto on 20/12/2022, 11:49
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 20/12/2022, 11:49
 *
 */

package com.core.di

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer(body))