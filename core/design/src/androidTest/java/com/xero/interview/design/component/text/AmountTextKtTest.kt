package com.xero.interview.design.component.text

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.xero.interview.utils.utils.toPriceFormat
import org.junit.Rule
import org.junit.Test

class AmountTextKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testAmountTextComposable() {
        val testAmount = 42.99

        composeTestRule.setContent {
            AmountText(amount = testAmount)
        }

        composeTestRule.onNodeWithText(testAmount.toPriceFormat())
            .assertIsDisplayed()
            .assertTextEquals(testAmount.toPriceFormat())
    }
}