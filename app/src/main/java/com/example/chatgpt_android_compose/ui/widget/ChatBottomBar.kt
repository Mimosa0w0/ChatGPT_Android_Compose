package com.example.chatgpt_android_compose.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.chatgpt_android_compose.ChatViewModel
import com.example.chatgpt_android_compose.R
import com.example.chatgpt_android_compose.requestModel.Mode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatBottomBar(viewModel: ChatViewModel) {
    var textFieldValue by remember {
        mutableStateOf("")
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.Gray)
            .shadow(elevation = 2.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp, horizontal = 15.dp),
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                },
                placeholder = {
                    Text(
                        text = "Write here",
                        color = Color.Gray
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            if (textFieldValue.isNotEmpty()) {
                                when (viewModel.mode) {
                                    Mode.Chat -> viewModel.chat(textFieldValue)
                                    Mode.Completions -> viewModel.completions(textFieldValue)
                                    Mode.Images -> viewModel.images(textFieldValue)
                                }
                                textFieldValue = ""
                            }
                        },
                        imageVector = ImageVector.vectorResource(id = R.drawable.baseline_send_24),
                        contentDescription = null
                    )
                }
            )
        }
    }
}