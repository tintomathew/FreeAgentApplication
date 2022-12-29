/*
 * *
 *  * Created by tinto on 20/12/2022, 11:41
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 20/12/2022, 11:41
 *
 */

package com.tinto.freeagentapplication

import com.core.di.Navigator
import com.core.di.Screen
import com.salt.spacex.GoToSpaceXActivity

class DefaultNavigatorProvider : Navigator.Provider {

    override fun get(screen: Screen): Navigator = when (screen) {
        is Screen.SpaceXScreen -> GoToSpaceXActivity("12")
        else -> {GoToSpaceXActivity("")}
    }
}