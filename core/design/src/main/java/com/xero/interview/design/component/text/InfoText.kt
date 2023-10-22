package com.xero.interview.design.component.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.xero.interview.design.theme.infoColorDark
import com.xero.interview.design.theme.subTitleText


@Composable
fun InfoText(modifier: Modifier = Modifier, style: TextStyle = subTitleText.copy(color = infoColorDark), text: String) {
    Text(modifier = modifier, text = text, style = style, maxLines = 1)
}

@Composable
@Preview
fun PreviewInfoText() {
    InfoText(modifier = Modifier.fillMaxWidth(), text = "Sub Title")
}