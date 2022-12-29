/*
 * *
 *  * Created by tinto on 20/12/2022, 11:46
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 20/12/2022, 11:29
 *
 */

package com.core.di

import android.app.Activity

interface Navigator {

    fun navigate(activity: Activity)

    interface Provider {

        fun get(screen: Screen): Navigator
    }
}

sealed class Screen {
    class SpaceXScreen(val userId: String) : Screen()
}