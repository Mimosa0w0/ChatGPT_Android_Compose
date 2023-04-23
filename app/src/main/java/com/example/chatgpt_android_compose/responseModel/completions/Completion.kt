package com.example.chatgpt_android_compose.responseModel.completions

data class Completion(
    val id: String,
    val created: Long,
    val choices: List<Choice>
)
