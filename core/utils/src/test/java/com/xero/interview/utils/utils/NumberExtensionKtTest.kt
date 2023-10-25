package com.xero.interview.utils.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class NumberExtensionKtTest {
    @Test
    fun `test null double to price format`() {
        val price: Double? = null
        val formattedPrice = price.toPriceFormat()
        assertThat(formattedPrice).isEqualTo("0.00")
    }

    @Test
    fun `test double conversion to price format`() {
        val price: Double = 1234567.89
        val formattedPrice = price.toPriceFormat()
        assertThat(formattedPrice).isEqualTo("1,234,567.89")
    }

    @Test
    fun `test zero double to price format`() {
        val price: Double = 0.0
        val formattedPrice = price.toPriceFormat()
        assertThat(formattedPrice).isEqualTo("0.00")
    }

    @Test
    fun `test negative double to price format`() {
        val price: Double = -987.6543
        val formattedPrice = price.toPriceFormat()
        assertThat(formattedPrice).isEqualTo("-987.65")
    }
}