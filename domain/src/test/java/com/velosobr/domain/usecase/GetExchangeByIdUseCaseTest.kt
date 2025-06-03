package com.velosobr.domain.usecase

import com.velosobr.core.error.AppException
import com.velosobr.core.result.ExchangeResult
import com.velosobr.domain.repository.ExchangeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetExchangeByIdUseCaseTest {

    private lateinit var repository: ExchangeRepository
    private lateinit var useCase: GetExchangeByIdUseCase

    private val fakeExchange = ExchangeFactory.create()

    @Before
    fun setup() {
        repository = mockk()
        useCase = GetExchangeByIdUseCase(repository)
    }

    @Test
    fun `should return Success when repository returns exchange`() = runTest {
        // Arrange
        coEvery { repository.getExchangeById("binance") } returns ExchangeResult.Success(
            fakeExchange
        )

        // Act
        val result = useCase("binance")

        // Assert
        assertTrue(result is ExchangeResult.Success)
        assertEquals(fakeExchange, (result as ExchangeResult.Success).data)

        coVerify(exactly = 1) { repository.getExchangeById("binance") }
    }

    @Test
    fun `should return Error when repository fails`() = runTest {
        // Arrange
        coEvery { repository.getExchangeById("unknown") } returns ExchangeResult.Error(
            AppException.Unknown(
                Throwable("Exchange not found")
            )
        )

        // Act
        val result = useCase("unknown")

        // Assert
        assertTrue(result is ExchangeResult.Error)
        assertEquals("Exchange not found", (result as ExchangeResult.Error).exception.message)

        coVerify(exactly = 1) { repository.getExchangeById("unknown") }
    }
}