package com.velosobr.exchange_list.presentation

import ExchangeFactory
import app.cash.turbine.test
import com.velosobr.core.error.AppException
import com.velosobr.core.result.ExchangeResult
import com.velosobr.core.state.UiState
import com.velosobr.domain.usecase.GetExchangeIconUrlUseCase
import com.velosobr.domain.usecase.GetExchangesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ExchangeListViewModelTest {

    private lateinit var getExchangesUseCase: GetExchangesUseCase
    private lateinit var getExchangeIconsUseCase: GetExchangeIconUrlUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getExchangesUseCase = mockk()
        getExchangeIconsUseCase = mockk()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should emit Loading then Success when use case returns data`() = runTest {
        // Arrange
        val exchanges = ExchangeFactory.createList(3)
        coEvery { getExchangesUseCase() } returns ExchangeResult.Success(exchanges)
        coEvery { getExchangeIconsUseCase(any()) } returns "https://example.com/icon.png"
        val viewModel = ExchangeListViewModel(getExchangesUseCase, getExchangeIconsUseCase)

        // Act
        viewModel.uiState.test {
            // Assert
            val loading = awaitItem()
            assertTrue(loading is UiState.Loading)

            val success = awaitItem()
            assertTrue(success is UiState.Success)
            assertEquals(exchanges, (success as UiState.Success).data)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should emit Loading then Error when use case throws exception`() = runTest {
        // Arrange
        val errorMessage = "API error"
        coEvery { getExchangesUseCase() } returns ExchangeResult.Error(
            AppException.Unknown(Throwable(errorMessage))
        )
        val viewModel = ExchangeListViewModel(getExchangesUseCase, getExchangeIconsUseCase)

        // Act
        viewModel.uiState.test {
            // Assert
            assertTrue(awaitItem() is UiState.Loading)

            val errorItem = awaitItem()
            assertTrue(errorItem is UiState.Error)
            assertEquals(errorMessage, (errorItem as UiState.Error).message)

            cancelAndIgnoreRemainingEvents()
        }
    }
}