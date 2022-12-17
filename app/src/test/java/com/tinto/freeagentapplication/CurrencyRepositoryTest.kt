package com.tinto.freeagentapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tinto.freeagentapplication.data.repo.model.CurrencyDateModel
import com.tinto.freeagentapplication.data.repo.model.RateModel
import com.tinto.freeagentapplication.data.repo.model.Rates
import com.tinto.freeagentapplication.data.repo.CurrencyRepository
import com.tinto.freeagentapplication.service.CurrencyApiService
import com.tinto.freeagentapplication.util.Resource
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response


@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class CurrencyRepositoryTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @MockK
    var cakesApiService: CurrencyApiService = mockk()

    // class under test
    lateinit var cakesRepository: CurrencyRepository

    @Before
    fun setup() {
        cakesRepository = CurrencyRepository(cakesApiService)
    }

    @Test
    fun currencyRateSuccessTest() {
        val rateModel = RateModel(
            success = true,
            timestamp = 1234,
            base = "http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg",
            date = "",
            rates = Rates()
        )
        coEvery { cakesApiService.getCurrencyRates(any(), any()) } returns Response.success(
            rateModel
        )
        runBlocking {
            val response = cakesRepository.getCurrencyRates("", "")
            Assert.assertEquals("Currency rate success test failed", rateModel, response.data)
        }
    }

    @Test
    fun currencyRateErrorTest() {
        coEvery { cakesApiService.getCurrencyRates(any(), any()) } returns Response.error(
            400,
            "{\"key\":[\"error\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        runBlocking {
            val response = cakesRepository.getCurrencyRates("", "")
            Assert.assertTrue("Currency rate error message test failed", response is Resource.Error)
        }
    }

    @Test
    fun currencyRateByDateSuccessTest() {
        val currencyDateModel = CurrencyDateModel(
            success = true,
            timeseries = true,
            base = "http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg",
            start_date = "",
            end_date = "",
            rates = HashMap()
        )
        coEvery {
            cakesApiService.getCurrencyRateByDate(
                any(),
                any(),
                any(),
                any()
            )
        } returns Response.success(currencyDateModel)
        runBlocking {
            val response = cakesRepository.getCurrencyRateByDate("", "", "", "")
            Assert.assertEquals(
                "Currency rate by date Success test failed",
                currencyDateModel,
                response.data
            )
        }
    }

    @Test
    fun currencyRateByDateErrorTest() {
        coEvery {
            cakesApiService.getCurrencyRateByDate(
                any(),
                any(),
                any(),
                any()
            )
        } returns Response.error(
            400,
            "{\"key\":[\"error\"]}"
                .toResponseBody("application/json".toMediaTypeOrNull())
        )
        runBlocking {
            val response = cakesRepository.getCurrencyRateByDate("", "", "", "")
            Assert.assertTrue(
                "Currency rate by date error message test failed",
                response is Resource.Error
            )
        }
    }
}