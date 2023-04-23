package com.example.chatgpt_android_compose.requestModel

import com.example.chatgpt_android_compose.requestModel.chat.ChatRequest
import com.example.chatgpt_android_compose.requestModel.completions.CompletionsRequest
import com.example.chatgpt_android_compose.requestModel.images.ImagesRequest
import okhttp3.MediaType.Companion.toMediaType

object Requester {

    val JSON = "application/json; charset=utf-8".toMediaType()

    val completionsRequest = CompletionsRequest()
    val chatRequest = ChatRequest()
    val imagesRequest = ImagesRequest()

}