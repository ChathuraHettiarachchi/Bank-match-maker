package com.xero.interview.design.component.cell


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.design.component.indicator.MatchFoundIndicator
import com.xero.interview.design.component.text.AmountText
import com.xero.interview.design.component.text.InfoText
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSpace
import com.xero.interview.design.component.utils.WSeparator
import com.xero.interview.design.theme.amountText
import com.xero.interview.design.theme.blackColor
import com.xero.interview.design.theme.defaultMargin
import com.xero.interview.design.theme.halfMargin
import com.xero.interview.design.theme.primary900
import com.xero.interview.design.theme.titleText
import com.xero.interview.design.theme.whiteColor

@Composable
fun TransactionCell(
    record: TransactionRecord,
    isMatched: Boolean = false,
    isChecked: Boolean = false,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = whiteColor)
                .padding(
                    end = defaultMargin,
                    top = defaultMargin,
                    bottom = defaultMargin,
                    start = halfMargin
                )
                .clickable { onClick }
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
                        TitleText(text = record.name, style = titleText.copy(color = blackColor))
                        InfoText(text = record.date)
                    }
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.End
                    ) {
                        AmountText(amount = record.amount, style = amountText)
                        InfoText(text = record.type)
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
        WSeparator()
    }
}

@Preview
@Composable
fun PreviewTransactionCell() {
    val data = TransactionRecord(
        0, "Test name", "12 Dec 2023", "Payment", 1234.12, 0, 0
    )
    TransactionCell(record = data)
}

@Preview
@Composable
fun PreviewTransactionClickedCell() {
    val data = TransactionRecord(
        0, "Test name", "12 Dec 2023", "Payment", 1234.12, 0, 0
    )
    TransactionCell(
        record = data,
        isChecked = true
    )
}

@Preview
@Composable
fun PreviewTransactionMatchedCell() {
    val data = TransactionRecord(
        0, "Test name", "12 Dec 2023", "Payment", 1234.12, 0, 0
    )
    TransactionCell(
        record = data,
        isMatched = true
    )
}