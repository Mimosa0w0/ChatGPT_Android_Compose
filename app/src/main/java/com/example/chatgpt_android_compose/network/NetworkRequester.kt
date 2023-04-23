package com.example.chatgpt_android_compose.network

import android.util.Log
import com.example.chatgpt_android_compose.network.networkService.ChatService
import com.example.chatgpt_android_compose.network.networkService.CompletionsService
import com.example.chatgpt_android_compose.network.networkService.ImagesService
import com.example.chatgpt_android_compose.responseModel.ChatResult
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object NetworkRequester {

    private val completionsService = AIServiceCreator.retrofit.create<CompletionsService>()
    private val chatService = AIServiceCreator.retrofit.create<ChatService>()
    private val imagesService = AIServiceCreator.retrofit.create<ImagesService>()

    suspend fun completions(header: String, body: RequestBody) = completionsService.completions(header, body).await()
    suspend fun chat(header: String, body: RequestBody) = chatService.chat(header, body).await()
    suspend fun images(header: String, body: RequestBody) = imagesService.images(header, body).await()

    private suspend fun <T> Call<T>.await(): ChatResult {
        return suspendCoroutine {
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body != null) it.resume(ChatResult.Successful(body))
                        else it.resume(ChatResult.Failure(response.code(), response.message()))
                    } else {
                        Log.d("Mimosa", response.message() + response.code())
                        it.resume(ChatResult.Failure(response.code(), response.message()))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    t.printStackTrace()
                    it.resume(ChatResult.Failure(message = t.message.toString()))
                }
            })
        }
    }

}