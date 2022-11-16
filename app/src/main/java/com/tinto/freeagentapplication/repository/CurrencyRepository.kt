/*
 * *
 *  * Created by tinto on 16/11/2022, 11:08
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 10:39
 *
 */

package com.tinto.freeagentapplication.repository

import com.tinto.freeagentapplication.model.CurrencyDateModel
import com.tinto.freeagentapplication.model.RateModel
import com.tinto.freeagentapplication.service.CurrencyApiService
import com.tinto.freeagentapplication.util.Resource
import retrofit2.Response
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val currencyApiService: CurrencyApiService) {
    suspend fun getCurrencyRates(
        symbols: String, base: String
    ): Resource<RateModel> {
        return responseToResource(currencyApiService.getCurrencyRates(symbols, base))
    }

    suspend fun getCurrencyRateByDate(
        startDate: String, endDate: String, symbols: String, base: String
    ): Resource<CurrencyDateModel> {
        return responseToDateResource(
            currencyApiService.getCurrencyRateByDate(
                startDate,
                endDate,
                symbols,
                base
            )
        )
    }

    private fun responseToResource(dataModel: Response<RateModel>): Resource<RateModel> {
        if (dataModel.isSuccessful) {
            dataModel.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(dataModel.message())
    }

    private fun responseToDateResource(dataModel: Response<CurrencyDateModel>): Resource<CurrencyDateModel> {
        if (dataModel.isSuccessful) {
            dataModel.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(dataModel.message())
    }
}