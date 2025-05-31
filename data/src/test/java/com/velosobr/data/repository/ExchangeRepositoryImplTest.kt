import com.velosobr.core.error.AppException
import com.velosobr.core.result.ExchangeResult
import com.velosobr.data.remote.api.CoinApiService
import com.velosobr.data.remote.dto.ExchangeDto
import com.velosobr.data.repository.ExchangeRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ExchangeRepositoryImplTest {

    private val api = mockk<CoinApiService>()
    private val apiKey = "fake-api-key"
    private lateinit var repository: ExchangeRepositoryImpl

    @Before
    fun setUp() {
        repository = ExchangeRepositoryImpl(api, apiKey)
    }

    @Test
    fun `should return Success when API returns valid data`() = runTest {
        // Arrange
        val dtoList = listOf(
            ExchangeDto(
                exchangeId = "OKCOINCNY",
                website = "https://www.okcoin.cn/",
                name = "OKCoin CNY",
                dataQuoteStart = "2015-02-15T12:53:50.3430000Z",
                dataQuoteEnd = "2018-03-09T23:34:52.5800000Z",
                dataOrderbookStart = "2015-02-15T12:53:50.3430000Z",
                dataOrderbookEnd = "2018-03-09T23:34:52.5800000Z",
                dataTradeStart = "2013-06-12T14:24:24.0000000Z",
                dataTradeEnd = "2017-11-01T16:30:39.7077259Z",
                dataSymbolsCount = 2,
                volume1HrsUsd = 0.0,
                volume1DayUsd = 0.0,
                volume1MthUsd = 0.0,
                rank = 1
            ),
            ExchangeDto(
                exchangeId = "binance",
                website = "https://www.binance.com",
                name = "Binance",
                dataQuoteStart = "2017-07-14T00:00:00Z",
                dataQuoteEnd = "2023-10-01T00:00:00Z",
                dataOrderbookStart = "2017-07-14T00:00:00Z",
                dataOrderbookEnd = "2023-10-01T00:00:00Z",
                dataTradeStart = "2017-07-14T00:00:00Z",
                dataTradeEnd = "2023-10-01T00:00:00Z",
                dataSymbolsCount = 1000,
                volume1HrsUsd = 5000000.0,
                volume1DayUsd = 100000000.0,
                volume1MthUsd = 3000000000.0,
                rank = 1
            ),

            )
        coEvery { api.getExchanges(apiKey) } returns dtoList

        // Act
        val result = repository.getExchanges()

        // Assert
        assertTrue(result is ExchangeResult.Success)
        val data = (result as ExchangeResult.Success).data
        assertEquals("OKCOINCNY", data.first().exchangeId)
        assertEquals("OKCoin CNY", data.first().name)
    }

    @Test
    fun `should return Error when API throws exception`() = runTest {
        // Arrange
        coEvery { api.getExchanges(apiKey) } throws RuntimeException("Timeout")

        // Act
        val result = repository.getExchanges()

        // Assert
        assertTrue(result is ExchangeResult.Error)
        assertTrue((result as ExchangeResult.Error).exception is AppException.Unknown)
    }
}