package com.xero.interview.design.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

// set of text styles used in the app
val titleText = TextStyle(
    fontSize = titleSize,
    fontWeight = FontWeight.SemiBold,
    color = whiteColor
)

val subTitleText = TextStyle(
    fontSize = subTitleSize,
    color = infoColor
)

val infoText = TextStyle(
    fontSize = subTitleSize,
    color = infoColor
)

val amountLargeText = TextStyle(
    fontSize = amountLargeSize,
    fontWeight = FontWeight.Bold,
    color = whiteColor,
    textAlign = TextAlign.Center
)

val amountText = TextStyle(
    fontSize = amountNormalSize,
    color = blackColor,
    textAlign = TextAlign.Center
)