package com.xero.interview.design.component.utils

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SeperatorKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testWSeparator() {
        composeTestRule.setContent {
            WSeparator()
        }

        composeTestRule.onNodeWithTag("WSeparator").assertExists()
    }

    @Test
    fun testHSeparator() {
        composeTestRule.setContent {
            HSeparator()
        }

        composeTestRule.onNodeWithTag("HSeparator").assertExists()
    }

}