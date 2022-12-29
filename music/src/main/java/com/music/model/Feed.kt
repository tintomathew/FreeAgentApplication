/*
 * *
 *  * Created by Tinto Mathew on 24/11/2022, 14:31
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 24/11/2022, 14:31
 *
 */

package com.music.model

import com.google.gson.annotations.SerializedName

data class Feed(
    @SerializedName("author") val author: Author,
    @SerializedName("entry") var entry: List<Entry>,
    @SerializedName("icon") val icon: Icon,
    @SerializedName("id") val id: IdX,
    @SerializedName("link") val link: List<Link>,
    @SerializedName("rights") val rights: Rights,
    @SerializedName("title") val title: Title,
    @SerializedName("updated") val updated: Updated
)