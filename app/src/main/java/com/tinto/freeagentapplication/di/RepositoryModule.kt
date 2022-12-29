/*
 * *
 *  * Created by tinto on 19/12/2022, 11:01
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 19/12/2022, 11:01
 *
 */

package com.tinto.freeagentapplication.di

import com.core.di.Navigator
import com.core.di.Screen
import com.tinto.freeagentapplication.data.repo.CurrencyRepository
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