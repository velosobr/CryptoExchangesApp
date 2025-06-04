package com.velosobr.domain.usecase

import com.velosobr.core.error.AppException
import com.velosobr.core.result.ExchangeResult
import com.velosobr.domain.repository.ExchangeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetExchangesUseCaseTest {

    private val repository = mockk<ExchangeRepository>()
    private lateinit var useCase: GetExchangesUseCase

    @Before
    fun setUp() {
        useCase = GetExchangesUseCase(repository)
    }

    @Test
    fun `should return Success when repository returns list of exchanges`() = runTest {
        // Arrange
        val exchanges = ExchangeFactory.createExchangeList(3)

        coEvery { repository.getExchanges() } returns ExchangeResult.Success(exchanges)

        // Act
        val result = useCase()

        // Assert
        assertTrue(result is ExchangeResult.Success)
        assertEquals(exchanges, (result as ExchangeResult.Success).data)
    }

    @Test
    fun `should return Error when repository throws exception`() = runTest {
        // Arrange
        val exception = AppException.Unknown(Throwable("API error"))
        coEvery { repository.getExchanges() } returns ExchangeResult.Error(exception)

        // Act
        val result = useCase()

        // Assert
        assertTrue(result is ExchangeResult.Error)
        assertEquals(exception, (result as ExchangeResult.Error).exception)
    }

    @Test
    fun `should return Error when network exception occurs`() = runTest {
        // Arrange
        val networkException = AppException.Network
        coEvery { repository.getExchanges() } returns ExchangeResult.Error(networkException)

        // Act
        val result = useCase()

        // Assert
        assertTrue(result is ExchangeResult.Error)
        assertEquals(networkException, (result as ExchangeResult.Error).exception)
    }
}