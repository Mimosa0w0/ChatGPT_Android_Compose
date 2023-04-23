package com.example.chatgpt_android_compose.responseModel

data class Message(
    val viewType: Int,
    val message: String,
    val sendBy: String,
    val isError: Boolean = false
) {
    companion object {
        const val SENT_BY_BOT = "bot"
        const val SENT_BY_ME = "me"
        const val TYPE_COMPLETION = 0
        const val TYPE_IMAGES = 1
    }
}