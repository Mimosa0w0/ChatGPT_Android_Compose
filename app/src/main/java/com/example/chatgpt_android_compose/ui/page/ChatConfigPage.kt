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
fun ChatConfigPage(context: Context) {
    var mModel by remember {
        mutableStateOf(Requester.chatRequest.model)
    }
    var temperature by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConfigPageTopBar(
            title = "ChatConfig",
            onSave = {
                if (temperature.isNotEmpty()) Requester.chatRequest.temperature = temperature.toDouble()
                Toast.makeText(context, "保存成功", Toast.LENGTH_SHORT).show()
            }
        )
        SelectMenu(
            title = "模型",
            currentSelect = mModel,
            items = emptyList(),
            onSelect = {
                mModel = it
            }
        )
        ConfigInput(
            title = "温度",
            placeholder = "范围为0.0 ~ 2.0",
            currentInput = temperature,
            upDateInput = {
                temperature = it
            }
        )
    }
}