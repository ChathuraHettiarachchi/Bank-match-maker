package com.xero.interview.design.component.cell


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.xero.interview.design.component.indicator.MatchFoundIndicator
import com.xero.interview.design.component.text.AmountText
import com.xero.interview.design.component.text.InfoText
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSpace
import com.xero.interview.design.theme.amountText
import com.xero.interview.design.theme.blackColor
import com.xero.interview.design.theme.defaultMargin
import com.xero.interview.design.theme.primary900
import com.xero.interview.design.theme.titleText
import com.xero.interview.design.theme.whiteColor

@Composable
fun TransactionCell(
    title: String,
    subTitle: String,
    amount: Double,
    type: String,
    isMatched: Boolean = false,
    isChecked: Boolean = false,
    onClick: (id: Long) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = whiteColor)
            .padding(defaultMargin)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = {},
                enabled = false,
                colors = CheckboxDefaults.colors(
                    checkedColor = primary900,
                    disabledCheckedColor = primary900
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(verticalArrangement = Arrangement.Center) {
                    TitleText(text = title, style = titleText.copy(color = blackColor))
                    InfoText(text = subTitle)
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    AmountText(amount = amount, style = amountText)
                    InfoText(text = type)
                }
            }
        }
        if (isMatched) {
            HSpace()
            MatchFoundIndicator(
                title = "",
                subTitle = "",
                amount = 0.0,
                type = "",
                isFullUI = false
            )
        }
    }
}

@Preview
@Composable
fun PreviewTransactionCell() {
    TransactionCell(title = "Title", subTitle = "Subtitle", amount = 5000.12, type = "Invoice")
}

@Preview
@Composable
fun PreviewTransactionClickedCell() {
    TransactionCell(
        title = "Title",
        subTitle = "Subtitle",
        amount = 5000.12,
        type = "Invoice",
        isChecked = true
    )
}

@Preview
@Composable
fun PreviewTransactionMatchedCell() {
    TransactionCell(
        title = "Title",
        subTitle = "Subtitle",
        amount = 5000.12,
        type = "Invoice",
        isMatched = true
    )
}