/*
 * *
 *  * Created by Tinto Mathew on 24/11/2022, 14:36
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 24/11/2022, 13:54
 *
 */

package com.music.service

import com.music.model.PlaylistData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface PlayListApiService {
    @GET("https://itunes.apple.com/us/rss/topalbums/limit=100/json")
    suspend fun getPlayList(): Response<PlaylistData>
}