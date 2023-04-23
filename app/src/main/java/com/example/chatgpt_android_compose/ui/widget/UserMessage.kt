package com.example.chatgpt_android_compose.ui.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserMessage(message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 15.dp, start = 80.dp, bottom = 5.dp, top = 5.dp)
    ) {
        Surface(
            modifier = Modifier
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(size = 5.dp))
                .align(Alignment.End),
            color = Color.LightGray
        ) {
            SelectionContainer {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = message,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            }
        }
    }
}