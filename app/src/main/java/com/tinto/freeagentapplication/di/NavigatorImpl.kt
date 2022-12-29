/*
 * *
 *  * Created by tinto on 20/12/2022, 14:09
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 20/12/2022, 14:09
 *
 */

package com.tinto.freeagentapplication.di

import com.core.di.Navigator
import com.core.di.Screen
import javax.inject.Inject

class NavigatorImpl @Inject constructor() : Navigator.Provider{
    override fun get(screen: Screen): Navigator {
        TODO("Not yet implemented")
    }
}