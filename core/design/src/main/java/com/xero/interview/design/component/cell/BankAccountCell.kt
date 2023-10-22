package com.xero.interview.design.component.cell

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xero.interview.design.R
import com.xero.interview.design.component.indicator.FindMatchIndicator
import com.xero.interview.design.component.text.AmountText
import com.xero.interview.design.component.text.InfoText
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSeparator
import com.xero.interview.design.component.utils.HSpace
import com.xero.interview.design.component.utils.WSeparator
import com.xero.interview.design.component.utils.WSpace
import com.xero.interview.design.theme.amountMediumSize
import com.xero.interview.design.theme.amountText
import com.xero.interview.design.theme.blackColor
import com.xero.interview.design.theme.defaultMargin
import com.xero.interview.design.theme.greenColor
import com.xero.interview.design.theme.titleText
import com.xero.interview.design.theme.whiteColor

@Composable
fun BankAccountCell(
    title: String,
    infoText: String,
    amountLeft: Double,
    amountRight: Double,
    icon: Int,
    isFindMatchNeeded: Boolean = false
) {
    Box {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = whiteColor)
                    .padding(defaultMargin)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(greenColor),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = icon),
                                contentScale = ContentScale.Crop,
                                contentDescription = "",
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                            )
                        }
                        WSpace(defaultMargin)
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            TitleText(text = title, style = titleText.copy(color = blackColor))
                            InfoText(text = infoText)
                        }
                    }
                    HSpace(defaultMargin)
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = Alignment.Start) {
                            AmountText(
                                amount = amountLeft,
                                style = amountText.copy(fontSize = amountMediumSize)
                            )
                            InfoText(text = "Xero's Balance")
                        }
                        HSeparator()
                        Column(horizontalAlignment = Alignment.End) {
                            AmountText(
                                amount = amountLeft,
                                style = amountText.copy(fontSize = amountMediumSize)
                            )
                            InfoText(text = "Statement's Balance")
                        }
                    }
                }
            }
            WSeparator()
        }
        if(isFindMatchNeeded)
            FindMatchIndicator()
    }
}

@Preview
@Composable
fun PreviewBankAccount() {
    BankAccountCell("Test tile", "Test subTitle", 12123.12, 123412.33, R.drawable.ic_001)
}