package com.example.chatgpt_android_compose.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AIServiceCreator {

    private const val BASE_URL = "https://api.openai.com/v1/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}