/*
 * *
 *  * Created by tinto on 16/11/2022, 11:08
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 10:39
 *
 */

package com.tinto.freeagentapplication.di

import com.tinto.freeagentapplication.repository.CurrencyRepository
import com.tinto.freeagentapplication.service.CurrencyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCurrencyRepository(currencyApiService: CurrencyApiService): CurrencyRepository {
        return CurrencyRepository(currencyApiService)
    }
}