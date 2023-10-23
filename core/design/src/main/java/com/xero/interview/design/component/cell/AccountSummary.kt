package com.xero.interview.design.component.cell

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xero.interview.design.component.text.AmountText
import com.xero.interview.design.component.text.SubTitleText
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSpace
import com.xero.interview.design.theme.defaultMargin
import com.xero.interview.design.theme.halfMargin
import com.xero.interview.design.theme.infoText
import com.xero.interview.design.theme.primary900
import com.xero.interview.design.theme.whiteColor

@Composable
fun AccountSummary(
    title: String,
    subTitle: String,
    amount: Double,
    lastUpdated: String = "dd MMM yyyy"
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 200.dp),
        colors = CardDefaults.cardColors(
            containerColor = primary900
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(halfMargin),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            HSpace(defaultMargin)
            TitleText(text = title)
            HSpace(defaultMargin)
            AmountText(amount = amount)
            HSpace(halfMargin)
            SubTitleText(text = subTitle, style = infoText.copy(color = whiteColor))
        }
    }
}

@Preview
@Composable
fun PreviewAccountSummary() {
    AccountSummary("All accounts", "-- last updated on 23 Dec 2023 --", 190234.99)
}