/*
 * *
 *  * Created by tinto on 16/11/2022, 11:07
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 15/11/2022, 11:09
 *
 */

package com.tinto.freeagentapplication.di

import com.google.gson.GsonBuilder
import com.tinto.freeagentapplication.BuildConfig
import com.tinto.freeagentapplication.service.CurrencyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private val READ_TIMEOUT = 3000
    private val WRITE_TIMEOUT = 3000
    private val CONNECTION_TIMEOUT = 3000

    @Provides
    @Singleton
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        headerInterceptor: Interceptor
    ): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient().newBuilder()
        okHttpClientBuilder.connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
        okHttpClientBuilder.addInterceptor(headerInterceptor)

        return okHttpClientBuilder.build()
    }


    @Provides
    @Singleton
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor {
            val requestBuilder = it.request().newBuilder()
                .addHeader("content-type", "text/plain;charset=UTF-8")
                .addHeader("authorization", "application/json")
                .addHeader("apikey", BuildConfig.AUTH_KEY)
            it.proceed(requestBuilder.build())
        }
    }


    @Provides
    @Singleton
    fun provideImageApiService(retrofit: Retrofit): CurrencyApiService {
        return retrofit.create(CurrencyApiService::class.java)
    }
}