package com.example.chatgpt_android_compose.network.networkService

import com.example.chatgpt_android_compose.responseModel.images.Images
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ImagesService {

    @POST("images/generations")
    fun images(@Header("Authorization") header: String, @Body body: RequestBody): Call<Images>

}