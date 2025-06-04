import com.velosobr.core.error.AppException
import com.velosobr.core.result.ExchangeResult
import com.velosobr.data.remote.api.ExchangeApiService
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

    private val api = mockk<ExchangeApiService>()
    private val apiKey = "fake-api-key"
    private lateinit var repository: ExchangeRepositoryImpl

    @Before
    fun setUp() {
        repository = ExchangeRepositoryImpl(api, apiKey)
    }

    @Test
    fun `should return Success when API returns valid data`() = runTest {
        // Arrange
        val exchangeList = listOf(
            ExchangeDto(
                exchangeId = "BINANCE",
                website = "https://www.binance.com",
                name = "Binance",
                dataStart = "2020-01-01",
                dataEnd = "2023-01-01",
                dataQuoteStart = "2020-01-01T00:00:00Z",
                dataQuoteEnd = "2023-01-01T00:00:00Z",
                dataOrderbookStart = "2020-01-01T00:00:00Z",
                dataOrderbookEnd = "2023-01-01T00:00:00Z",
                dataTradeStart = "2020-01-01T00:00:00Z",
                dataTradeEnd = "2023-01-01T00:00:00Z",
                dataTradeCount = 1000000,
                dataSymbolsCount = 500,
                volume1HrsUsd = 100000.0,
                volume1DayUsd = 2400000.0,
                volume1MthUsd = 72000000.0,
                metricId = listOf("metric1", "metric2"),
                icons = listOf(),
                rank = 1,
                integrationStatus = "active"
            ),
            ExchangeDto(
                exchangeId = "COINBASE",
                website = "https://www.coinbase.com",
                name = "Coinbase",
                dataStart = "2019-01-01",
                dataEnd = "2023-01-01",
                dataQuoteStart = "2019-01-01T00:00:00Z",
                dataQuoteEnd = "2023-01-01T00:00:00Z",
                dataOrderbookStart = "2019-01-01T00:00:00Z",
                dataOrderbookEnd = "2023-01-01T00:00:00Z",
                dataTradeStart = "2019-01-01T00:00:00Z",
                dataTradeEnd = "2023-01-01T00:00:00Z",
                dataTradeCount = 500000,
                dataSymbolsCount = 300,
                volume1HrsUsd = 50000.0,
                volume1DayUsd = 1200000.0,
                volume1MthUsd = 36000000.0,
                metricId = listOf("metric3", "metric4"),
                icons = listOf(),
                rank = 2,
                integrationStatus = "active"
            ),
            ExchangeDto(
                exchangeId = "KRAKEN",
                website = "https://www.kraken.com",
                name = "Kraken",
                dataStart = "2018-01-01",
                dataEnd = "2023-01-01",
                dataQuoteStart = "2018-01-01T00:00:00Z",
                dataQuoteEnd = "2023-01-01T00:00:00Z",
                dataOrderbookStart = "2018-01-01T00:00:00Z",
                dataOrderbookEnd = "2023-01-01T00:00:00Z",
                dataTradeStart = "2018-01-01T00:00:00Z",
                dataTradeEnd = "2023-01-01T00:00:00Z",
                dataTradeCount = 750000,
                dataSymbolsCount = 400,
                volume1HrsUsd = 75000.0,
                volume1DayUsd = 1800000.0,
                volume1MthUsd = 54000000.0,
                metricId = listOf("metric5", "metric6"),
                icons = listOf(),
                rank = 3,
                integrationStatus = "active"
            )
        )
        coEvery { api.getExchanges(apiKey) } returns exchangeList

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