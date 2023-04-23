package com.example.chatgpt_android_compose.requestModel.chat

import com.example.chatgpt_android_compose.responseModel.chat.ChatMessage

data class ChatRequest(
    val model: String = "gpt-3.5-turbo",
    var messages: List<ChatMessage> = emptyList(),
    var temperature: Double? = null,
    var topP: Double? = null,
    var stream: Boolean? = null,
    var stop: List<String>? = null
)
