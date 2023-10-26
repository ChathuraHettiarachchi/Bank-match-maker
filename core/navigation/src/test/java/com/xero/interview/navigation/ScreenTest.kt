package com.xero.interview.navigation

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ScreenTest {
    @Test
    fun `Screen Home should have correct route`() {
        assertThat(Screen.Home.route).isEqualTo("home")
    }

    @Test
    fun `Screen AccountRecord should have correct route`() {
        assertThat(Screen.AccountRecord.route).isEqualTo("account_record")
    }

    @Test
    fun `Screen TransactionRecord should have correct route`() {
        assertThat(Screen.TransactionRecord.route).isEqualTo("transaction_record")
    }
}