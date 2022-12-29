/*
 * *
 *  * Created by tinto on 21/12/2022, 18:35
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 21/12/2022, 18:35
 *
 */

package com.music.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TestModel(var name: String): Parcelable
