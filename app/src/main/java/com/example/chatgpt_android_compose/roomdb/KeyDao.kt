package com.example.chatgpt_android_compose.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface KeyDao {

    @Insert
    fun insertKey(key: Key)

    @Delete
    fun deleteKey(key: Key)

    @get:Query("SELECT * FROM `key`")
    val key: Key?

}