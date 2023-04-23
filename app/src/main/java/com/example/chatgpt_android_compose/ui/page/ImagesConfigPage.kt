package com.example.chatgpt_android_compose.ui.page

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.chatgpt_android_compose.requestModel.Requester
import com.example.chatgpt_android_compose.ui.widget.ConfigInput
import com.example.chatgpt_android_compose.ui.widget.ConfigPageTopBar
import com.example.chatgpt_android_compose.ui.widget.SelectMenu

@Composable
fun ImagesConfigPage(context: Context) {
    var mSize by remember {
        mutableStateOf(Requester.imagesRequest.size)
    }
    var count by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConfigPageTopBar(
            title = "ImagesConfig",
            onSave = {
                Requester.imagesRequest.run {
                    size = mSize
                    if (count.isNotEmpty() && count.toInt() in 1..10) n = count.toInt()
                }
                Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show()
            }
        )
        SelectMenu(
            title = "生成大小",
            currentSelect = mSize,
            items = listOf("256x256", "512x512", "1024x1024"),
            onSelect = {
                mSize = it
            }
        )
        ConfigInput(
            title = "生成数量",
            placeholder = "范围为1 ~ 10",
            currentInput = count,
            upDateInput = {
                count = it
            }
        )
    }
}