package com.xero.interview.utils.utils

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

/**
 * Will change double value to a formatted price. Can use to add 2 fixed decimals and format
 * the number with 3 digit separator ","
 */
fun Double?.toPriceFormat(): String {
    if (this == null)
        return "0.00"

    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    val decimalFormat = numberFormat as DecimalFormat

    // Set the number of decimal places
    decimalFormat.applyPattern("#,##0.00")

    return decimalFormat.format(this)
}