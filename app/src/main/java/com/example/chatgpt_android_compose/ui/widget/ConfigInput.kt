package com.example.chatgpt_android_compose.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfigInput(
    title: String,
    placeholder: String,
    currentInput: String,
    upDateInput: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 20.dp)
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .width(100.dp)
                .padding(start = 30.dp),
            text = title,
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        TextField(
            modifier = Modifier
                .width(200.dp)
                .padding(start = 50.dp),
            value = currentInput,
            onValueChange = {
                upDateInput(it)
            },
            placeholder = {
                Text(
                    text = placeholder,
                    color = Color.Gray
                )
            },
            singleLine = true
        )
    }
}