/*
 * *
 *  * Created by Tinto Mathew on 24/11/2022, 14:31
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 24/11/2022, 14:31
 *
 */

package com.music.model

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("im:id") val id: String?,
    @SerializedName("label") val label: String?,
    @SerializedName("scheme") val scheme: String?,
    @SerializedName("term") val term: String?,
    @SerializedName("href") val href: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("amount") val amount: String?,
    @SerializedName("currency") val currency: String?,
    @SerializedName("rel") val rel: String?,
    @SerializedName("type") val type: String?
)