/*
 * *
 *  * Created by tinto on 20/12/2022, 11:48
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 20/12/2022, 11:48
 *
 */

package com.core.di

/**
 * Used as a wrapper for data that is exposed via a LiveData that represents an event.
 * Based on the proposal in this post:
 * https://medium.com/androiddevelopers/livedata-with-snackbar-navigation-and-other-events-the-singleliveevent-case-ac2622673150
 */
open class Event<out T>(private val content: T) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}