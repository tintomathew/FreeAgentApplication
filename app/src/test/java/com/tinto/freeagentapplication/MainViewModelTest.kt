package com.tinto.freeagentapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tinto.freeagentapplication.home.MainViewModel
import com.tinto.freeagentapplication.data.repo.model.CurrencyDateModel
import com.tinto.freeagentapplication.data.repo.model.CurrencyModel
import com.tinto.freeagentapplication.data.repo.model.RateModel
import com.tinto.freeagentapplication.data.repo.model.Rates
import com.tinto.freeagentapplication.data.repo.CurrencyRepository
import com.tinto.freeagentapplication.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var coroutinesTestRule = CoroutineTestRule()

    @MockK
    var currencyRepository: CurrencyRepository = mockk()

    // class under test
    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = MainViewModel(currencyRepository)
        viewModel.sdf = mockk()
        every { viewModel.sdf.format(any()) } returns "2022-11-11"
        every { viewModel.getStartDate() } returns "2022-11-11"
        every { viewModel.getCurrentDate() } returns "2022-11-16"
    }

    @Test
    fun showProgressTest() {
        Assert.assertTrue(
            "progress indicator test failed",
            viewModel.isLoading.value == true
        )
    }

    @Test
    fun currencyRateResponseTest() {
        val rateModel = RateModel(
            success = true,
            timestamp = 1234,
            base = "http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg",
            date = "",
            rates = Rates(USD = 1234.0)
        )
        coEvery { currencyRepository.getCurrencyRates(any(), any()) } returns Resource.Success(
            rateModel
        )
        coroutinesTestRule.testDispatcher.run {
            viewModel.getCurrency("EUR")
            Assert.assertEquals(
                "Currency rate test failed" + viewModel.responseData.size,
                CurrencyModel(isSelected = false, name = "USD", value = 1234.0),
                viewModel.responseData.get(0)
            )
        }
    }

    @Test
    fun currencyRateErrorResponseTest() {
        coEvery { currencyRepository.getCurrencyRates(any(), any()) } returns Resource.Error("")
        coroutinesTestRule.testDispatcher.run {
            viewModel.getCurrency("EUR")
            Assert.assertTrue(
                "Currency rate error test failed" + viewModel.error.value,
                viewModel.error.value == true
            )
        }
    }

    @Test
    fun currencyRateByDateResponseTest() {
        val values: HashMap<String, Rates> = HashMap()
        values.put("2022-12-15", Rates())
        val currencyDateModel = CurrencyDateModel(
            success = true,
            timeseries = true,
            base = "http://ukcdn.ar-cdn.com/recipes/xlarge/ff22df7f-dbcd-4a09-81f7-9c1d8395d936.jpg",
            start_date = "2022-12-15",
            end_date = "",
            rates = values
        )
        coEvery {
            currencyRepository.getCurrencyRateByDate(
                any(),
                any(),
                any(),
                any()
            )
        } returns Resource.Success(currencyDateModel)
        coroutinesTestRule.testDispatcher.run {
            viewModel.getCurrencyRateByDate()
            Assert.assertEquals(
                "Currency rate by date test failed" + viewModel.responseData.size,
                "",
                viewModel.historyList[0].rates
            )
        }
    }

    @Test
    fun currencyRateByDateErrorResponseTest() {
        coEvery {
            currencyRepository.getCurrencyRateByDate(
                any(),
                any(),
                any(),
                any()
            )
        } returns Resource.Error("")
        coroutinesTestRule.testDispatcher.run {
            viewModel.getCurrencyRateByDate()
            Assert.assertTrue(
                "Currency rate by date error test failed" + viewModel.error.value,
                viewModel.error.value == true
            )
        }
    }
}