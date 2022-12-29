/*
 * *
 *  * Created by tinto on 05/12/2022, 10:59
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 05/12/2022, 10:59
 *
 */

package com.tinto.freeagentapplication.data.repo

import android.util.Log
import com.tinto.freeagentapplication.data.repo.model.CurrencyDateModel
import com.tinto.freeagentapplication.data.repo.model.RateModel
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