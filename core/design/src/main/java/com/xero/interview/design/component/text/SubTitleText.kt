package com.xero.interview.design.component.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.xero.interview.design.theme.subTitleText


/**
 * SubTitleText can use to display text with some preset values
 * @param modifier can send defined modifier from where you use
 * @param style use to update the textStyle values
 * @param text change subTitle
 */
@Composable
fun SubTitleText(modifier: Modifier = Modifier, style: TextStyle = subTitleText, text: String) {
    Text(modifier = modifier, text = text, style = style, maxLines = 1)
}

@Composable
@Preview
fun PreviewSubTitleText() {
    SubTitleText(modifier = Modifier.fillMaxWidth(), text = "Sub Title")
}