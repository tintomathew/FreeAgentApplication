/*
 * *
 *  * Created by tinto on 05/12/2022, 10:58
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 11:10
 *
 */

package com.tinto.freeagentapplication.data.repo.model

import com.google.gson.annotations.SerializedName

data class CurrencyDateModel(
    @SerializedName("success") val success: Boolean,
    @SerializedName("timeseries") val timeseries: Boolean,
    @SerializedName("start_date") val start_date: String,
    @SerializedName("end_date") val end_date: String,
    @SerializedName("base") val base: String,
    @SerializedName("rates") val rates: Map<String, Rates> = HashMap()
)