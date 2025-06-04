package com.velosobr.exchange_list

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.velosobr.core.state.UiState
import com.velosobr.exchange_list.presentation.ExchangeListContent
import org.junit.Rule
import org.junit.Test

class ExchangeListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun titleIsDisplayed() {
        composeTestRule.setContent {
            ExchangeListContent(
                uiState = UiState.Loading,
                iconMap = emptyMap(),
                onExchangeClick = { _, _ -> },
                onRetryClick = {}
            )
        }

        composeTestRule
            .onNodeWithText("Exchanges List")
            .assertIsDisplayed()
    }

    @Test
    fun listDisplaysExchangeItemOnSuccess() {
        val mockExchange = ExchangeFactory.create("binance", "Binance", 999.9)

        composeTestRule.setContent {
            ExchangeListContent(
                uiState = UiState.Success(listOf(mockExchange)),
                iconMap = mapOf("binance" to "https://example.com/icon.png"),
                onExchangeClick = { _, _ -> },
                onRetryClick = {}
            )
        }

        composeTestRule
            .onNodeWithText("Binance")
            .assertIsDisplayed()
    }

    @Test
    fun clickingExchangeItemCallsOnClick() {
        var clickedId: String? = null
        var clickedUrl: String? = null

        val mockExchange = ExchangeFactory.create("binance", "Binance", 999.9)

        composeTestRule.setContent {
            ExchangeListContent(
                uiState = UiState.Success(listOf(mockExchange)),
                iconMap = mapOf("binance" to "https://example.com/icon.png"),
                onExchangeClick = { id, url ->
                    clickedId = id
                    clickedUrl = url
                },
                onRetryClick = {}
            )
        }

        composeTestRule
            .onNodeWithText("Binance")
            .performClick()

        assert(clickedId == "binance")
        assert(clickedUrl == "https://example.com/icon.png")
    }
}