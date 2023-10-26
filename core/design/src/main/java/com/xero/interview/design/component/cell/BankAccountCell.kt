package com.xero.interview.design.component.cell

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xero.interview.data.domain.model.BankAccount
import com.xero.interview.design.R
import com.xero.interview.design.component.indicator.FindMatchIndicator
import com.xero.interview.design.component.text.AmountText
import com.xero.interview.design.component.text.InfoText
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSeparator
import com.xero.interview.design.component.utils.HSpace
import com.xero.interview.design.component.utils.WSpace
import com.xero.interview.design.theme.amountMediumSize
import com.xero.interview.design.theme.amountText
import com.xero.interview.design.theme.background
import com.xero.interview.design.theme.blackColor
import com.xero.interview.design.theme.defaultMargin
import com.xero.interview.design.theme.greenColor
import com.xero.interview.design.theme.halfRadius
import com.xero.interview.design.theme.infoText
import com.xero.interview.design.theme.moneyOutColor
import com.xero.interview.design.theme.subTitleText
import com.xero.interview.design.theme.titleText
import com.xero.interview.design.theme.whiteColor

/**
 * BankAccountCell used to display bank account information
 * @param account account to display
 * @param infoText small text
 * @param onClick action trigger
 */
@Composable
fun BankAccountCell(
    account: BankAccount,
    infoText: Int = 0,
    onClick: (Long) -> Unit = {}
) {
    val _infoText =
        if (infoText > 0) "$infoText transactions to match" else "All transactions matched"

    Box(modifier = Modifier.clickable { onClick(account.id) }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = background)
                .padding(defaultMargin),
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
                            painter = painterResource(id = R.drawable.ic_003),
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
                        TitleText(text = account.name, style = titleText.copy(color = blackColor))
                        InfoText(text = _infoText, style = subTitleText.copy(
                            color = if(infoText>0) moneyOutColor else greenColor
                        ))
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
                            amount = account.appBalance,
                            style = amountText.copy(fontSize = amountMediumSize)
                        )
                        InfoText(text = "Xero's Balance")
                    }
                    HSeparator()
                    Column(horizontalAlignment = Alignment.End) {
                        AmountText(
                            amount = account.statementBalance,
                            style = amountText.copy(fontSize = amountMediumSize)
                        )
                        InfoText(text = "Statement's Balance")
                    }
                }
            }
        }
        if (infoText>0)
            FindMatchIndicator()
    }
}

@Preview
@Composable
fun PreviewBankAccount() {
    BankAccountCell(
        account = BankAccount(
            0,
            R.drawable.ic_001,
            "Amana Bank NZ",
            92345.12,
            23425.00,
            "20 Oct 2023"
        )
    )
}