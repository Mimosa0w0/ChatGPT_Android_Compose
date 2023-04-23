package com.example.chatgpt_android_compose

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatgpt_android_compose.network.NetworkRequester
import com.example.chatgpt_android_compose.requestModel.Mode
import com.example.chatgpt_android_compose.requestModel.Requester.JSON
import com.example.chatgpt_android_compose.requestModel.Requester.chatRequest
import com.example.chatgpt_android_compose.requestModel.Requester.completionsRequest
import com.example.chatgpt_android_compose.requestModel.Requester.imagesRequest
import com.example.chatgpt_android_compose.responseModel.ChatResult
import com.example.chatgpt_android_compose.responseModel.Message
import com.example.chatgpt_android_compose.responseModel.chat.ChatCompletion
import com.example.chatgpt_android_compose.responseModel.chat.ChatMessage
import com.example.chatgpt_android_compose.responseModel.chat.ChatMessage.Companion.ROLE_USER
import com.example.chatgpt_android_compose.responseModel.completions.Completion
import com.example.chatgpt_android_compose.responseModel.images.Images
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import okhttp3.RequestBody.Companion.toRequestBody

class ChatViewModel : ViewModel() {

    private val _messageList = mutableStateListOf<Message>()
    val messageList: SnapshotStateList<Message> = _messageList
    private val chatMessageList = ArrayList<ChatMessage>()
    var mode = Mode.Chat
    private var key: String? = null
    private val bearer = "Bearer"

    fun completions(prompt: String) {
        viewModelScope.launch {
            _messageList.add(Message(Message.TYPE_COMPLETION, prompt, Message.SENT_BY_ME))
            completionsRequest.prompt = prompt
            val body = Gson().toJson(completionsRequest).toRequestBody(JSON)
            flow {
                key = MainActivity.dataBase?.KeyDao()?.key?.mkey
                emit(NetworkRequester.completions("$bearer $key", body))
            }.flowOn(Dispatchers.IO).collectLatest { result ->
                when (result) {
                    is ChatResult.Successful<*> -> {
                        val completion = result.data as Completion
                        completion.choices.forEach {
                            _messageList.add(Message(Message.TYPE_COMPLETION, it.text, Message.SENT_BY_BOT))
                        }
                    }
                    is ChatResult.Failure -> handleFailure(result)
                }
            }
        }
    }

    fun chat(content: String) {
        viewModelScope.launch {
            _messageList.add(Message(Message.TYPE_COMPLETION, content, Message.SENT_BY_ME))
            chatMessageList.add(ChatMessage(ROLE_USER, content))
            chatRequest.messages = chatMessageList.toList()
            val body = Gson().toJson(chatRequest).toRequestBody(JSON)
            flow {
                key = MainActivity.dataBase?.KeyDao()?.key?.mkey
                emit(NetworkRequester.chat("$bearer $key", body))
            }.flowOn(Dispatchers.IO).collectLatest { result ->
                when (result) {
                    is ChatResult.Successful<*> -> {
                        val chatCompletion = result.data as ChatCompletion
                        chatCompletion.choices.forEach {
                            _messageList.add(Message(Message.TYPE_COMPLETION, it.message.content, Message.SENT_BY_BOT))
                            chatMessageList.add(it.message)
                        }
                    }
                    is ChatResult.Failure -> handleFailure(result)
                }
            }
        }
    }

    fun images(prompt: String) {
        viewModelScope.launch {
            _messageList.add(Message(Message.TYPE_COMPLETION, prompt, Message.SENT_BY_ME))
            imagesRequest.prompt = prompt
            val body = Gson().toJson(imagesRequest).toRequestBody(JSON)
            flow {
                key = MainActivity.dataBase?.KeyDao()?.key?.mkey
                emit(NetworkRequester.images("$bearer $key", body))
            }.flowOn(Dispatchers.IO).collectLatest { result ->
                when (result) {
                    is ChatResult.Successful<*> -> {
                        val images = result.data as Images
                        images.data.forEach {
                            _messageList.add(Message(Message.TYPE_IMAGES, it.url, Message.SENT_BY_BOT))
                        }
                    }
                    is ChatResult.Failure -> handleFailure(result)
                }
            }
        }
    }

    private fun handleFailure(result: ChatResult.Failure) {
        if (result.code == 401) {
            _messageList.add(Message(Message.TYPE_COMPLETION, "error code: 401\n请前往设置配置API Key以继续使用该功能", Message.SENT_BY_BOT, true))
        } else {
            _messageList.add(Message(Message.TYPE_COMPLETION, "${result.code}\n${result.message}", Message.SENT_BY_BOT, true))
        }
    }

}