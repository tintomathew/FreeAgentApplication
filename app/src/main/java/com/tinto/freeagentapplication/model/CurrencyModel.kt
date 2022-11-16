/*
 * *
 *  * Created by tinto on 16/11/2022, 11:08
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 10:39
 *
 */

package com.tinto.freeagentapplication.model

data class CurrencyModel(
    var isSelected: Boolean = false,
    var name: String = "",
    var value: Double? = null,
)