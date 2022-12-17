/*
 * *
 *  * Created by tinto on 24/11/2022, 13:26
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 11:10
 *
 */

package com.tinto.freeagentapplication.service

import com.tinto.freeagentapplication.data.repo.model.CurrencyDateModel
import com.tinto.freeagentapplication.data.repo.model.RateModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("latest?")
    suspend fun getCurrencyRates(
        @Query("symbols") symbols: String,
        @Query("base") base: String
    ): Response<RateModel>

    @GET("timeseries?")
    suspend fun getCurrencyRateByDate(
        @Query("start_date") start_date: String,
        @Query("end_date") end_date: String,
        @Query("symbols") symbols: String,
        @Query("base") base: String
    ): Response<CurrencyDateModel>
}