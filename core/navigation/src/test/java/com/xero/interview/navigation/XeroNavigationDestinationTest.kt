package com.xero.interview.navigation

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class XeroNavigationDestinationTest {

    // temporary class to check route and destination
    private class TestNavigationDestination(
        override val route: String,
        override val destination: String
    ) : XeroNavigationDestination

    private lateinit var testNavigationDestination: TestNavigationDestination

    @Before
    fun setup() {
        testNavigationDestination =
            TestNavigationDestination(route = "test_route/1", destination = "test_destination")
    }

    @Test
    fun `test route property`() {
        assertThat(testNavigationDestination.route).isEqualTo("test_route/1")
    }

    @Test
    fun `test destination property`() {
        assertThat(testNavigationDestination.destination).isEqualTo("test_destination")
    }
}