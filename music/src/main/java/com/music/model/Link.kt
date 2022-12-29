/*
 * *
 *  * Created by Tinto Mathew on 24/11/2022, 14:31
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 24/11/2022, 14:31
 *
 */

package com.music.model

import com.google.gson.annotations.SerializedName
import com.music.model.Attributes

data class Link(
    @SerializedName("attributes") val attributes: Attributes
)