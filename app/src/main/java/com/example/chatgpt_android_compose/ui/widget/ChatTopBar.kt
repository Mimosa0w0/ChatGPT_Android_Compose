package com.example.chatgpt_android_compose.ui.widget

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatgpt_android_compose.ChatViewModel
import com.example.chatgpt_android_compose.R
import com.example.chatgpt_android_compose.requestModel.Mode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(
    context: Context,
    viewModel: ChatViewModel,
    onNavigateToKeyConfigPage: () -> Unit,
    onNavigateToConfigPage: () -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.White)
            .shadow(elevation = 2.dp),
        title = {
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                text = viewModel.mode,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        },
        actions = {
            Surface(
                modifier = Modifier
                    .size(60.dp, 25.dp)
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(size = 15.dp))
                    .clickable {
                        expanded = true
                    }
            ) {
                Text(
                    modifier = Modifier.wrapContentSize(Alignment.Center),
                    text = "模式",
                    color = Color.Black,
                    fontSize = 16.sp
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    val items = listOf(Mode.Chat, Mode.Completions, Mode.Images)
                    items.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = item,
                                    fontSize = 18.sp
                                )
                            },
                            onClick = {
                                expanded = false
                                viewModel.mode = item
                                onNavigateToConfigPage()
                                Toast.makeText(context, "$item Mode", Toast.LENGTH_SHORT).show()
                            }
                        )
                    }
                }
            }
            IconButton(
                onClick = onNavigateToKeyConfigPage,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.baseline_settings_24),
                    contentDescription = null
                )
            }
        }
    )
}