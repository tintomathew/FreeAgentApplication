/*
 * *
 *  * Created by tinto on 16/11/2022, 11:08
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 14/11/2022, 23:15
 *
 */

package com.tinto.freeagentapplication.listeners

import com.tinto.freeagentapplication.data.repo.model.CurrencyModel

// click listener for currency item click */
interface CurrencyItemClick {
    fun onCurrencyRateClick(currencyModel: CurrencyModel, position: Int)
}