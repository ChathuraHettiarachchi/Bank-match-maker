package com.xero.interview.design.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


const val defaultMarginValue: Int = 16
const val halfMarginValue: Int = defaultMarginValue / 2
const val quarterMarginValue: Int = defaultMarginValue / 4

val defaultMargin: Dp = defaultMarginValue.dp
val halfMargin: Dp = halfMarginValue.dp
val quarterMargin: Dp = quarterMarginValue.dp

const val defaultRadiusValue: Int = 16
const val halfRadiusValue: Int = defaultRadiusValue / 2
const val quarterRadiusValue: Int = defaultRadiusValue / 4

val defaultRadius: Dp = defaultRadiusValue.dp
val halfRadius: Dp = halfRadiusValue.dp
val quarterRadius: Dp = quarterRadiusValue.dp

val titleSize: TextUnit = 18.sp
val subTitleSize: TextUnit = 14.sp
val amountLargeSize: TextUnit = 56.sp
val amountNormalSize: TextUnit = 18.sp
val amountMediumSize: TextUnit = 24.sp