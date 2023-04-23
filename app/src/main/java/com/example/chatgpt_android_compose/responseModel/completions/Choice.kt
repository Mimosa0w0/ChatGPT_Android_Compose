package com.example.chatgpt_android_compose.responseModel.completions

data class Choice(
    val text: String,
    val index: Int,
    val finish_reason: String? = null
)
