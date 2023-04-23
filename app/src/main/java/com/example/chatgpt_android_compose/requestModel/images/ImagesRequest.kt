package com.example.chatgpt_android_compose.requestModel.images

data class ImagesRequest(
    var prompt: String = "",
    var n: Int? = null,
    var size: String = "256x256"
)