/*
 * *
 *  * Created by tinto on 16/11/2022, 11:08
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 10:39
 *
 */

package com.tinto.freeagentapplication.model

import com.google.gson.annotations.SerializedName

data class Rates(
    @SerializedName("USD") var USD: Double? = null,
    @SerializedName("EUR") var EUR: Double? = null,
    @SerializedName("JPY") var JPY: Double? = null,
    @SerializedName("GBP") var GBP: Double? = null,
    @SerializedName("AUD") var AUD: Double? = null,
    @SerializedName("CAD") var CAD: Double? = null,
    @SerializedName("CHF") var CHF: Double? = null,
    @SerializedName("CNY") var CNY: Double? = null,
    @SerializedName("SEK") var SEK: Double? = null,
    @SerializedName("NZD") var NZD: Double? = null
)