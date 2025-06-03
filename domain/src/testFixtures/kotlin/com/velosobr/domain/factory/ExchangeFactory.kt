import com.velosobr.domain.model.Exchange
import java.time.ZonedDateTime

object ExchangeFactory {

    fun create(
        exchangeId: String = "binance",
        name: String = "Binance",
        volume1dayUsd: Double = 1_000_000.0,
        iconUrl: String = "https://example.com/icon.png"
    ): Exchange {
        val now = ZonedDateTime.now()

        return Exchange(
            exchangeId = exchangeId,
            website = "https://www.$exchangeId.com",
            name = name,
            dataQuoteStart = now,
            dataQuoteEnd = now,
            dataOrderbookStart = now,
            dataOrderbookEnd = now,
            dataTradeStart = now,
            dataTradeEnd = now,
            dataSymbolsCount = 100,
            volume1hrsUsd = 100_000.0,
            volume1dayUsd = volume1dayUsd,
            volume1mthUsd = 50_000_000.0,
            rank = 1,
            iconUrl = iconUrl
        )
    }

    fun createList(size: Int): List<Exchange> {
        return (1..size).map {
            create(
                exchangeId = "exchange$it",
                name = "Exchange $it",
                volume1dayUsd = it * 1_000_000.0,
                iconUrl = "https://example.com/icon$it.png"
            )
        }
    }
}