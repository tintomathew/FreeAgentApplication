/*
 * *
 *  * Created by Tinto Mathew on 24/11/2022, 14:31
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 24/11/2022, 14:31
 *
 */

package com.music.model

import com.google.gson.annotations.SerializedName

data class Entry(
    @SerializedName("category") val category: Category,
    @SerializedName("id") val id: Id,
    @SerializedName("im:artist") val artist: ImArtist,
    @SerializedName("im:contentType") val contentType: ImContentType,
    @SerializedName("im:image") val image: List<ImImage>,
    @SerializedName("im:itemCount") val itemCount: ImItemCount,
    @SerializedName("im:name") val name: ImName,
    @SerializedName("im:price") val price: ImPrice,
    @SerializedName("im:releaseDate") val releaseDate: ImReleaseDate,
    @SerializedName("link") val link: Link,
    @SerializedName("rights") val rights: Rights,
    @SerializedName("title") val title: Title
)