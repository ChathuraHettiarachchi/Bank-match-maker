package com.xero.interview.design.component.indicator

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xero.interview.design.theme.greenColor
import com.xero.interview.design.theme.moneyOutColor
import com.xero.interview.design.theme.primary900

/**
 * FindMatchIndicator will used to displace a anchor on a view
 */
@Composable
fun FindMatchIndicator() {
    Canvas(modifier = Modifier
        .width(30.dp)
        .height(30.dp)
    ) {
        val path = Path()
        path.moveTo(0f, 0f)
        path.lineTo(size.width, 0f)
        path.lineTo(0f, size.height)

        drawPath(
            path = path,
            brush = SolidColor(moneyOutColor)
        )
    }
}

@Preview
@Composable
fun PreviewFindMatchIndicator() {
    FindMatchIndicator()
}