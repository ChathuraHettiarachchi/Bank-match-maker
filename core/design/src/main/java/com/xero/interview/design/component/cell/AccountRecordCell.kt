package com.xero.interview.design.component.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xero.interview.data.domain.model.AccountRecord
import com.xero.interview.data.domain.model.TransactionRecord
import com.xero.interview.design.component.indicator.MatchFoundIndicator
import com.xero.interview.design.component.text.AmountText
import com.xero.interview.design.component.text.InfoText
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSpace
import com.xero.interview.design.component.utils.WSeparator
import com.xero.interview.design.component.utils.WSpace
import com.xero.interview.design.icon.XeroIcons
import com.xero.interview.design.theme.amountText
import com.xero.interview.design.theme.blackColor
import com.xero.interview.design.theme.defaultMargin
import com.xero.interview.design.theme.halfMargin
import com.xero.interview.design.theme.moneyInColor
import com.xero.interview.design.theme.moneyOutColor
import com.xero.interview.design.theme.titleText
import com.xero.interview.design.theme.whiteColor

/**
 * AccountRecordCell display the account record values
 * @param record is the record that gonna populate in the cell
 * @param matchedRecord if there are matched records use this, then it will show the match
 * found component attached to the cell
 * @param onClick handle click, will take bank and account id
 */
@Composable
fun AccountRecordCell(
    record: AccountRecord,
    matchedRecord: List<TransactionRecord>? = null,
    onClick: (Long, Long) -> Unit = { p1, p2 -> }
) {
    val color = if (record.amount > 0) moneyInColor else moneyOutColor
    val icon = if (record.amount > 0) XeroIcons.In else XeroIcons.Out

    Column(modifier = Modifier.clickable { onClick(record.bankAccountId, record.id) }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = whiteColor)
                .padding(defaultMargin)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 60.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Row {
                    Box(
                        modifier = Modifier
                            .size(46.dp)
                            .clip(CircleShape)
                            .background(color = color),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(icon, contentDescription = record.name, tint = whiteColor)
                    }
                    WSpace(halfMargin)
                    Column(
                        verticalArrangement = Arrangement.Center
                    ) {
                        TitleText(text = record.name, style = titleText.copy(color = blackColor))
                        InfoText(text = record.date)
                    }
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    WSpace()
                    AmountText(amount = record.amount, style = amountText)
                }
            }
            if (!matchedRecord.isNullOrEmpty()) {
                HSpace()
                MatchFoundIndicator(
                    title = matchedRecord[0].name,
                    subTitle = matchedRecord[0].date,
                    amount = matchedRecord[0].amount,
                    type = matchedRecord[0].type,
                    isFullUI = true
                )
            }
        }
        WSeparator()
    }

}

@Preview
@Composable
fun PreviewAccountRecordInCell() {
    val data =
        AccountRecord((0..999).random().toLong(), "Test name", "12 Dec 2023", 50012.23, 12)
    AccountRecordCell(
        record = data
    )
}

@Preview
@Composable
fun PreviewAccountRecordOutCell() {
    val data =
        AccountRecord((0..999).random().toLong(), "Test name", "12 Dec 2023", 50012.23, 12)
    AccountRecordCell(
        record = data
    )
}

@Preview
@Composable
fun PreviewAccountRecordOutWithIndicatorCell() {
    val data =
        AccountRecord((0..999).random().toLong(), "Test name", "12 Dec 2023", 50012.23, 12)
    AccountRecordCell(
        record = data,
        matchedRecord = null
    )
}