/*
 * *
 *  * Created by Tinto Mathew on 25/11/2022, 13:39
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 25/11/2022, 13:39
 *
 */

package com.music.model

import com.google.gson.annotations.SerializedName
import com.music.model.Attributes

data class ImContentTypeSecondary(
    @SerializedName("attributes") val attributes: Attributes
)