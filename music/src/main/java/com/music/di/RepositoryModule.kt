package com.music.di

import com.music.repository.PlayListRepository
import com.music.service.PlayListApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePlayListRepository(playListApiService: PlayListApiService): PlayListRepository {
        return PlayListRepository(playListApiService)
    }
}