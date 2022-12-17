/*
 * *
 *  * Created by tinto on 05/12/2022, 10:58
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 11:10
 *
 */

package com.tinto.freeagentapplication.data.repo.model

import com.google.gson.annotations.SerializedName

data class RateModel(
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("timestamp") var timestamp: Int? = null,
    @SerializedName("base") var base: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("rates") var rates: Rates? = Rates()
)