package com.xero.interview.design.component.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xero.interview.design.R
import com.xero.interview.design.theme.greenColor
import com.xero.interview.design.theme.halfMargin
import com.xero.interview.design.theme.halfRadius
import com.xero.interview.design.theme.whiteColor
import com.xero.interview.utils.utils.toPriceFormat

/**
 * MatchesIndicator will used to display the amount to match
 * @param amount will be the amount to match
 */
@Composable
fun MatchesIndicator(amount: Double) {
    Box(
        modifier = Modifier
            .padding(start = halfMargin, end = halfMargin)
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
                .background(
                    color = greenColor,
                    shape = RoundedCornerShape(bottomEnd = halfRadius, bottomStart = halfRadius)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = halfMargin, end = halfMargin),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.matches),
                    style = TextStyle(color = whiteColor)
                )
                Text(
                    text = amount.toPriceFormat(),
                    style = TextStyle(color = whiteColor, fontWeight = FontWeight.Bold)
                )
                Text(
                    text = (" " + stringResource(id = R.string.matches_to_match)),
                    style = TextStyle(color = whiteColor)
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewMatchesIndicator() {
    MatchesIndicator(5000.00)
}