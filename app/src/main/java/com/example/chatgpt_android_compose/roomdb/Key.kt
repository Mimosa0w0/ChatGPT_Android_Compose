package com.example.chatgpt_android_compose.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "key")
data class Key(
    @PrimaryKey val id: Int = 0,
    val mkey: String
)
