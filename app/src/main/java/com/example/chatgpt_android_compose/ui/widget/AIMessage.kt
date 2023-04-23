package com.example.chatgpt_android_compose.ui.widget

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.chatgpt_android_compose.responseModel.Message

@Composable
fun AIMessage(message: Message) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 80.dp, start = 15.dp, bottom = 5.dp, top = 5.dp)
    ) {
        Surface(
            modifier = Modifier
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(size = 5.dp))
                .align(Alignment.Start),
            color = Color.DarkGray
        ) {
            when (message.viewType) {
                Message.TYPE_COMPLETION -> {
                    val textColor = if (message.isError) Color.Red else Color.White
                    SelectionContainer {
                        Text(
                            modifier = Modifier.padding(10.dp),
                            text = message.message,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            color = textColor
                        )
                    }
                }
                Message.TYPE_IMAGES -> {
                    AsyncImage(
                        model = message.message,
                        contentDescription = "image",
                        onError = {
                            Toast.makeText(context, "加载错误", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}