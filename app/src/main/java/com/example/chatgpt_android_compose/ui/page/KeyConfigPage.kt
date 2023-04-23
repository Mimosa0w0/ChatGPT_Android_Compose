package com.example.chatgpt_android_compose.ui.page

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatgpt_android_compose.MainActivity
import com.example.chatgpt_android_compose.roomdb.Key
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyConfigPage(context: Context) {
    val coroutineScope = rememberCoroutineScope()
    var key by remember {
        mutableStateOf("")
    }
    val keyRegex = Regex("^sk-[A-Za-z0-9]{48}\$")

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .width(300.dp)
                .height(70.dp),
            value = key,
            onValueChange = {
                key = it
            },
            placeholder = {
                Text(
                    text = "Please set Key",
                    color = Color.Gray
                )
            }
        )
        Button(
            modifier = Modifier.padding(20.dp),
            onClick = {
                if (key.isEmpty()) {
                    Toast.makeText(context, "Key不得为空！！！", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                if (!keyRegex.matches(key)) {
                    Toast.makeText(context, "Key格式错误！！！", Toast.LENGTH_SHORT).show()
                    return@Button
                }
                coroutineScope.launch(Dispatchers.IO) {
                    val keyDao = MainActivity.dataBase?.KeyDao()
                    val keyByDe = keyDao?.key
                    if (keyByDe == null) {
                        keyDao?.insertKey(Key(mkey = key))
                    } else if (keyByDe.mkey != key) {
                        keyDao.deleteKey(keyByDe)
                        keyDao.insertKey(Key(mkey = key))
                    }
                    key = ""
                    MainScope().launch {
                        Toast.makeText(context, "配置成功", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        ) {
            Text(
                text = "确定",
                fontSize = 18.sp
            )
        }
    }
}