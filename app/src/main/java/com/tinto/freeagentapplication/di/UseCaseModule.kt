/*
 * *
 *  * Created by tinto on 05/12/2022, 13:15
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 05/12/2022, 13:15
 *
 */

package com.tinto.freeagentapplication.di

import com.tinto.freeagentapplication.data.repo.CurrencyRepository
import com.tinto.freeagentapplication.domain.usecase.CurrencyUseCase
import com.tinto.freeagentapplication.service.CurrencyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideCurrencyUseCase(currencyRepository: CurrencyRepository): CurrencyUseCase {
        return CurrencyUseCase(currencyRepository)
    }
}