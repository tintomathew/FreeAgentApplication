/*
 * *
 *  * Created by tinto on 16/11/2022, 11:08
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 10:39
 *
 */

package com.tinto.freeagentapplication.model

import com.google.gson.annotations.SerializedName

data class RateModel(
    @SerializedName("success") var success: Boolean? = null,
    @SerializedName("timestamp") var timestamp: Int? = null,
    @SerializedName("base") var base: String? = null,
    @SerializedName("date") var date: String? = null,
    @SerializedName("rates") var rates: Rates? = Rates()
)