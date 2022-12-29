package com.music.di

import com.google.gson.GsonBuilder
import com.music.service.PlayListApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providePlayListApiService(retrofit: Retrofit): PlayListApiService {
        return retrofit.create(PlayListApiService::class.java)
    }
}