package com.music.repository

import com.music.model.PlaylistData
import com.music.service.PlayListApiService
import com.music.util.Resource
import retrofit2.Response
import javax.inject.Inject

class PlayListRepository @Inject constructor(private val playListApiService: PlayListApiService) {
    suspend fun getPlayList(): Resource<PlaylistData> {
        return responseToResource(playListApiService.getPlayList())
    }

    private fun responseToResource(dataModel: Response<PlaylistData>): Resource<PlaylistData> {
        if (dataModel.isSuccessful) {
            dataModel.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(dataModel.message())
    }
}