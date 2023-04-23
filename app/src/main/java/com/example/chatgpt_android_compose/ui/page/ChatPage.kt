package com.example.chatgpt_android_compose.ui.page

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.chatgpt_android_compose.ChatViewModel
import com.example.chatgpt_android_compose.ui.widget.ChatBottomBar
import com.example.chatgpt_android_compose.ui.widget.ChatList
import com.example.chatgpt_android_compose.ui.widget.ChatTopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ChatPage(
    context: Context,
    viewModel: ChatViewModel,
    onNavigateToKeyConfigPage: () -> Unit,
    onNavigateToConfigPage: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ChatTopBar(context, viewModel, onNavigateToKeyConfigPage, onNavigateToConfigPage)
        },
        bottomBar = {
            ChatBottomBar(viewModel)
        }
    ) {
        ChatList(viewModel.messageList)
    }
}