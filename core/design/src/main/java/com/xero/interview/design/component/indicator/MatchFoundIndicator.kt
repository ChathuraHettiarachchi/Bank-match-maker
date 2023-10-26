package com.xero.interview.design.component.indicator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.xero.interview.design.R
import com.xero.interview.design.component.text.AmountText
import com.xero.interview.design.component.text.InfoText
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSpace
import com.xero.interview.design.component.utils.WSpace
import com.xero.interview.design.theme.amountText
import com.xero.interview.design.theme.blackColor
import com.xero.interview.design.theme.greenColor
import com.xero.interview.design.theme.halfMargin
import com.xero.interview.design.theme.halfRadius
import com.xero.interview.design.theme.lightGreenColor
import com.xero.interview.design.theme.subTitleSize
import com.xero.interview.design.theme.subTitleText
import com.xero.interview.design.theme.titleText

/**
 * MatchFoundIndicator will be used to display matched content in the screen
 * @param title change title text
 * @param subTitle change the sub text
 * @param amount
 * @param type can use to display the type value
 * @param isFullUI this is boolean and can toggle between full and other small view
 */
@Composable
fun MatchFoundIndicator(
    title: String,
    subTitle: String,
    amount: Double,
    type: String?,
    isFullUI: Boolean = true
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = lightGreenColor, shape = RoundedCornerShape(halfRadius))

    ) {
        Box(modifier = Modifier.padding(halfRadius)) {
            if (isFullUI) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(halfMargin)) {
                    MatchFoundText()
                    HSpace()
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {
                            TitleText(
                                text = "$type: $title",
                                style = titleText.copy(color = blackColor, fontSize = subTitleSize)
                            )
                            InfoText(text = subTitle)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            WSpace()
                            AmountText(amount = amount, style = amountText.copy(fontSize = subTitleSize))
                        }
                    }
                }
            } else {
                MatchFoundText()
            }
        }
    }
}

@Composable
fun MatchFoundText() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            painter = painterResource(id = R.drawable.ic_attachment),
            contentDescription = "",
            tint = greenColor
        )
        WSpace()
        InfoText(
            text = stringResource(id = R.string.matched_transaction),
            style = subTitleText.copy(color = greenColor)
        )
    }
}

@Preview
@Composable
fun PreviewMatchFoundIndicator() {
    MatchFoundIndicator(
        title = "This is the title",
        subTitle = "12 Dec 2023",
        amount = 1234.12,
        type = "Payment",
        true
    )
}

@Preview
@Composable
fun PreviewMatchFoundIndicatorSmall() {
    MatchFoundIndicator(
        title = "This is the title",
        subTitle = "12 Dec 2023",
        amount = 1234.12,
        type = "Payment",
        false
    )
}