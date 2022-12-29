/*
 * *
 *  * Created by Tinto Mathew on 24/11/2022, 13:35
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 24/11/2022, 13:11
 *
 */

package com.music.util

// A generic class that contains data and status about loading this data.
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}