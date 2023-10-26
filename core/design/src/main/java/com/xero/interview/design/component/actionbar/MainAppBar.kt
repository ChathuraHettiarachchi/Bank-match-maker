package com.xero.interview.design.component.actionbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xero.interview.design.component.text.TitleText
import com.xero.interview.design.theme.*

/**
 * Main app bar with title
 */
@Composable
fun MainAppBar(text: String){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp).background(color = primary900), contentAlignment = Alignment.Center) {
        TitleText(text = text)
    }
}

@Preview
@Composable
fun PreviewMainAppBar(){
    MainAppBar(text = "Title")
}