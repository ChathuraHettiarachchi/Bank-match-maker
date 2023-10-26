package com.xero.interview.design.component.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Vertical space with default size 8
 * @param size to define spacing
 */
@Composable
fun HSpace(size: Dp = 8.dp) {
    Spacer(Modifier.height(size))
}

/**
 * Horizontal space with default size 8
 * @param size to define spacing
 */
@Composable
fun WSpace(size: Dp = 8.dp) {
    Spacer(Modifier.width(size))
}