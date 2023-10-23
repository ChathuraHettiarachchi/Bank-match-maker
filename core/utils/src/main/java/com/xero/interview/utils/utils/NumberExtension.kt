package com.xero.interview.utils.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

fun Double?.toPriceFormat(): String {
    if (this == null)
        return "0.00"

    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    val decimalFormat = numberFormat as DecimalFormat

    // Set the number of decimal places
    decimalFormat.applyPattern("#,###.##")
    
    return decimalFormat.format(this)
}