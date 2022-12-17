/*
 * *
 *  * Created by tinto on 05/12/2022, 11:47
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 05/12/2022, 11:47
 *
 */

package com.tinto.freeagentapplication.domain.usecase

import com.tinto.freeagentapplication.data.repo.CurrencyRepository
import com.tinto.freeagentapplication.data.repo.model.CurrencyDateModel
import com.tinto.freeagentapplication.data.repo.model.RateModel
import com.tinto.freeagentapplication.util.Resource
import javax.inject.Inject

class CurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {
    suspend fun getCurrencyRates(symbols: String, base: String): Resource<RateModel> {
        return currencyRepository.getCurrencyRates(symbols, base)
    }

    suspend fun getCurrencyRateByDate(
        startDate: String,
        endDate: String,
        symbols: String,
        base: String
    ): Resource<CurrencyDateModel> {
        return currencyRepository.getCurrencyRateByDate(startDate, endDate, symbols, base)
    }
}