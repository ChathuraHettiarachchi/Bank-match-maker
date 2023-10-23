package com.xero.interview.design.component.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import com.xero.interview.design.theme.amountLargeText
import com.xero.interview.design.theme.titleSize
import com.xero.interview.utils.utils.toPriceFormat

@Composable
fun AmountText(amount: Double, style: TextStyle = amountLargeText) {
    Text(text = amount.toPriceFormat(), style = style, maxLines = 1)
}


@Preview
@Composable
fun PreviewAmountText() {
    AmountText(amount = 19124.34)
}