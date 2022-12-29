/*
 * *
 *  * Created by tinto on 19/12/2022, 11:23
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 19/12/2022, 11:21
 *
 */

package com.tinto.freeagentapplication.di
import com.tinto.freeagentapplication.data.repo.CurrencyRepository
import com.tinto.freeagentapplication.domain.usecase.CurrencyUseCase
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