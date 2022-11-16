/*
 * *
 *  * Created by tinto on 16/11/2022, 11:08
 *  * Copyright (c) 2022 . All rights reserved.
 *  * Last modified 16/11/2022, 10:39
 *
 */

package com.tinto.freeagentapplication.home

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tinto.freeagentapplication.model.CurrencyModel
import com.tinto.freeagentapplication.model.HistoryRateModel
import com.tinto.freeagentapplication.model.Rates
import com.tinto.freeagentapplication.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val currencyRepository: CurrencyRepository
) :
    ViewModel() {

    private val DATE_FORMAT = "yyyy-MM-dd"
    var sdf = SimpleDateFormat(DATE_FORMAT)

    // hold the currency response data
    var responseData: ArrayList<CurrencyModel> = ArrayList()

    // hold the currency response data for the last 5 days
    var historyList: ArrayList<HistoryRateModel> = ArrayList()

    // hold the progress indicator status
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    // hold the error indicator status
    var error: MutableLiveData<Boolean> = MutableLiveData()

    // hold the selected currency value
    var selectedCurrency: String = "EUR"

    init {
        isLoading.postValue(true)
        error.postValue(false)
    }

    /**
     * Method containing a API call to fetch the currency rate based on the @property baseCurrency
     *
     * @property baseCurrency String indicates the base currency.
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getCurrency(baseCurrency: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val response =
                currencyRepository.getCurrencyRates(
                    "USD, EUR, JPY, GBP, AUD, CAD, CHF, CNY, SEK, NZD",
                    baseCurrency
                )
            response.data?.let {
                responseData.clear()
                responseData.add(CurrencyModel(false, "USD", it.rates?.USD))
                responseData.add(CurrencyModel(false, "EUR", it.rates?.EUR?.toDouble()))
                responseData.add(CurrencyModel(false, "JPY", it.rates?.JPY))
                responseData.add(CurrencyModel(false, "GBP", it.rates?.GBP))
                responseData.add(CurrencyModel(false, "AUD", it.rates?.AUD))
                responseData.add(CurrencyModel(false, "CAD", it.rates?.CAD))
                responseData.add(CurrencyModel(false, "CHF", it.rates?.CHF))
                responseData.add(CurrencyModel(false, "CNY", it.rates?.CNY))
                responseData.add(CurrencyModel(false, "SEK", it.rates?.SEK))
                responseData.add(CurrencyModel(false, "NZD", it.rates?.NZD))
            }

            response.message?.let {
                error.postValue(true)
            }
            isLoading.postValue(false)
        }
    }

    /**
     * Method containing a API call to fetch the currency rate based on the given date
     *
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getCurrencyRateByDate() {
        historyList.clear()
        isLoading.postValue(true)
        viewModelScope.launch {
            val responseByDate = currencyRepository.getCurrencyRateByDate(
                getStartDate(),
                getCurrentDate(),
                getFilterInput(),
                selectedCurrency
            )
            responseByDate.data?.let {
                responseByDate.data.rates.entries.forEach {
                    historyList.add(HistoryRateModel(it.key, getHistoryRate(it.value)))
                }
            }

            responseByDate.message?.let {
                error.postValue(true)
            }
            isLoading.postValue(false)
        }
    }

    /**
     * Method to create the currency date string
     *
     * @return currency rate of last 5 days.
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    private fun getHistoryRate(value: Rates): String {
        var historyRate = ""
        value.USD?.let {
            historyRate = historyRate.plus("USD : ".plus(value.USD.toString()).plus("\n"))
        }
        value.AUD?.let {
            historyRate = historyRate.plus("AUD : ".plus(value.AUD.toString()).plus("\n"))
        }
        value.EUR?.let {
            historyRate = historyRate.plus("EUR : ".plus(value.EUR.toString()).plus("\n"))
        }
        value.JPY?.let {
            historyRate = historyRate.plus("JPY : ".plus(value.JPY.toString()).plus("\n"))
        }
        value.GBP?.let {
            historyRate = historyRate.plus("GBP : ".plus(value.GBP.toString()).plus("\n"))
        }
        value.CAD?.let {
            historyRate = historyRate.plus("CAD : ".plus(value.CAD.toString()).plus("\n"))
        }
        value.CHF?.let {
            historyRate = historyRate.plus("CHF : ".plus(value.CHF.toString()).plus("\n"))
        }
        value.CNY?.let {
            historyRate = historyRate.plus("CNY : ".plus(value.CNY.toString()).plus("\n"))
        }
        value.SEK?.let {
            historyRate = historyRate.plus("SEK : ".plus(value.SEK.toString()).plus("\n"))
        }
        value.NZD?.let {
            historyRate = historyRate.plus("NZD : ".plus(value.NZD.toString()).plus("\n"))
        }
        return historyRate
    }

    /**
     * Method to create the currency filter string
     *
     * @return currency filter string
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getFilterInput(): String {
        var name = ""
        responseData.filter { it.isSelected }.forEach {
            if (name.isEmpty()) {
                name = name.plus(it.name)
            } else {
                name = name.plus(",").plus(it.name)
            }
        }
        return name
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getCurrentDate(): String {
        return sdf.format(Date())
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun getStartDate(): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -5)
        val dateBefore5Days = cal.time
        return sdf.format(dateBefore5Days)
    }
}