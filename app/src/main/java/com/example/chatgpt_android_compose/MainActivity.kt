package com.example.chatgpt_android_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatgpt_android_compose.requestModel.Mode
import com.example.chatgpt_android_compose.roomdb.MyDatabase
import com.example.chatgpt_android_compose.ui.page.ChatConfigPage
import com.example.chatgpt_android_compose.ui.page.ChatPage
import com.example.chatgpt_android_compose.ui.page.CompletionsConfigPage
import com.example.chatgpt_android_compose.ui.page.ImagesConfigPage
import com.example.chatgpt_android_compose.ui.page.KeyConfigPage
import com.example.chatgpt_android_compose.ui.theme.ChatGPT_Android_ComposeTheme

class MainActivity : ComponentActivity() {

    companion object {
        var dataBase: MyDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatGPT_Android_ComposeTheme {
                PageNavHost()
            }
        }
        dataBase = MyDatabase.getInstance(this)
    }

}

@Composable
fun PageNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = "chatPage"
) {
    val viewModel: ChatViewModel = viewModel()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("chatPage") {
            ChatPage(
                context = context,
                viewModel = viewModel,
                onNavigateToKeyConfigPage = {
                    navController.navigate("keyConfigPage")
                },
                onNavigateToConfigPage = {
                    when (viewModel.mode) {
                        Mode.Chat -> navController.navigate("chatConfigPage")
                        Mode.Images -> navController.navigate("imagesConfigPage")
                        Mode.Completions -> navController.navigate("completionsConfigPage")
                    }
                }
            )
        }
        composable("keyConfigPage") {
            KeyConfigPage(context)
        }
        composable("chatConfigPage") {
            ChatConfigPage(context)
        }
        composable("imagesConfigPage") {
            ImagesConfigPage(context)
        }
        composable("completionsConfigPage") {
            CompletionsConfigPage(context)
        }
    }
}