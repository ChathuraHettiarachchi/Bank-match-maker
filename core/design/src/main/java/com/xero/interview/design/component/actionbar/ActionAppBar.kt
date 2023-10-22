package com.xero.interview.design.component.actionbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xero.interview.design.component.indicator.MatchesIndicator
import com.xero.interview.design.component.text.SubTitleText
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.component.utils.HSpace
import com.xero.interview.design.icon.XeroIcons
import com.xero.interview.design.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionAppBar(text: String, subTitle: String?, onNavigationClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = primary900),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = halfMargin, start = halfMargin),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigationClick) {
                Icon(XeroIcons.Back, contentDescription = "", tint = whiteColor)
            }
            Column {
                TitleText(text = text)
                HSpace(2.dp)
                if (!subTitle.isNullOrEmpty())
                    SubTitleText(text = subTitle)
            }
        }
    }
}

@Preview
@Composable
fun PreviewActionAppBar() {
    ActionAppBar(text = "Title", subTitle = "SubTitle")
}

@Preview
@Composable
fun PreviewActionAppBarWithEmptySubtitle() {
    ActionAppBar(text = "Title", subTitle = "")
}

@Preview
@Composable
fun PreviewActionAppBarWithIndicator() {
    Column {
        ActionAppBar(text = "Title", subTitle = "Test sub title")
        MatchesIndicator(amount = 5000.00)
    }
}