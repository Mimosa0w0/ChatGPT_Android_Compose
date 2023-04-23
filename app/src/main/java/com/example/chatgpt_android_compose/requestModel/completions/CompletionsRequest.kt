package com.example.chatgpt_android_compose.requestModel.completions

data class CompletionsRequest(
    var model: String = "text-davinci-003",
    var prompt: String? = null,
    var maxTokens: Int? = null,
    var temperature: Double? = null
)
