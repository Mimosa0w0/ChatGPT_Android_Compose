package com.example.chatgpt_android_compose.network.networkService

import com.example.chatgpt_android_compose.responseModel.completions.Completion
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CompletionsService {

    @POST("completions")
    fun completions(@Header("Authorization") header: String, @Body body: RequestBody): Call<Completion>

}