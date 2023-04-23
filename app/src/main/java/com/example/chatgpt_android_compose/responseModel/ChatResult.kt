package com.example.chatgpt_android_compose.responseModel

interface ChatResult {
    data class Successful<T>(val data: T) : ChatResult
    data class Failure(val code: Int? = 0, val message: String) : ChatResult
}