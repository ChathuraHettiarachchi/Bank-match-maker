package com.xero.interview.design.component.utils

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xero.interview.design.theme.defaultMargin
import com.xero.interview.design.theme.halfMargin
import com.xero.interview.design.theme.separatorColor

@Composable
fun WSeparator(sidePadding: Dp = defaultMargin, color: Color = separatorColor) {
    Divider(
        modifier = Modifier
            .padding(start = sidePadding, end = sidePadding)
            .fillMaxWidth()
            .width(1.dp),
        color = color
    )
}

@Composable
fun HSeparator(sidePadding: Dp = halfMargin, color: Color = separatorColor) {
    Divider(
        modifier = Modifier
            .padding(top = sidePadding, bottom = sidePadding)
            .fillMaxHeight()
            .width(1.dp),
        thickness = 1.dp,
        color = color
    )
}