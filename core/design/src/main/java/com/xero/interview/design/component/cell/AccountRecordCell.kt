package com.xero.interview.design.component.cell

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xero.interview.design.R
import com.xero.interview.design.component.text.AmountText
import com.xero.interview.design.component.text.InfoText
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSeparator
import com.xero.interview.design.component.utils.WSpace
import com.xero.interview.design.icon.XeroIcons
import com.xero.interview.design.theme.amountNormalSize
import com.xero.interview.design.theme.amountText
import com.xero.interview.design.theme.blackColor
import com.xero.interview.design.theme.defaultMargin
import com.xero.interview.design.theme.greenColor
import com.xero.interview.design.theme.halfMargin
import com.xero.interview.design.theme.infoText
import com.xero.interview.design.theme.moneyInColor
import com.xero.interview.design.theme.moneyOutColor
import com.xero.interview.design.theme.titleText
import com.xero.interview.design.theme.whiteColor

@Composable
fun AccountRecordCell(title: String, subTitle: String, amount: Double,isMoneyIn: Boolean, matchedRecord: Any? = null) {
    val color = if(isMoneyIn) moneyInColor else moneyOutColor
    val icon = if(isMoneyIn) XeroIcons.In else XeroIcons.Out

    Box(
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
                    Icon( icon , contentDescription = title, tint = whiteColor)
                }
                WSpace(halfMargin)
                Column(

                    verticalArrangement = Arrangement.Center
                ) {
                    TitleText(text = title, style = titleText.copy(color = blackColor))
                    InfoText(text = subTitle)
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                WSpace()
                AmountText(amount = amount, style = amountText)
            }

        }
    }
}

@Preview
@Composable
fun PreviewAccountRecordInCell() {
    AccountRecordCell(title = "Title", subTitle = "Sub title", amount = 12341212.12, isMoneyIn = true)
}

@Preview
@Composable
fun PreviewAccountRecordOutCell() {
    AccountRecordCell(title = "Title", subTitle = "Sub title", amount = 12341212.12, isMoneyIn = false)
}