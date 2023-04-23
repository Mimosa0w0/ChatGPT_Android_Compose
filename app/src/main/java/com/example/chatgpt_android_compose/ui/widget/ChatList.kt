package com.example.chatgpt_android_compose.ui.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chatgpt_android_compose.responseModel.Message
import kotlinx.coroutines.launch

@Composable
fun ChatList(messageList: SnapshotStateList<Message>) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = Modifier
            .padding(top = 60.dp, bottom = 70.dp)
            .fillMaxSize(),
        state = scrollState,
        verticalArrangement = Arrangement.Bottom
    ) {
        items(messageList.size) {
            when (messageList[it].sendBy) {
                Message.SENT_BY_ME -> UserMessage(message = messageList[it].message)
                Message.SENT_BY_BOT -> AIMessage(message = messageList[it])
            }
        }
        coroutineScope.launch {
            messageList.size?.let {
                scrollState.animateScrollToItem(it)
            }
        }
    }
}