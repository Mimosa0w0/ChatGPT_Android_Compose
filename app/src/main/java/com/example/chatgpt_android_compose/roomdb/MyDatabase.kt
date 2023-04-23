package com.example.chatgpt_android_compose.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Key::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {

    abstract fun KeyDao(): KeyDao

    companion object {
        @Volatile
        private var sInstance: MyDatabase? = null
        private const val DATA_BASE_NAME = "key.db"

        @JvmStatic
        fun getInstance(context: Context): MyDatabase? {
            if (sInstance == null) {
                synchronized(Database::class.java) {
                    if (sInstance == null) {
                        sInstance = createInstance(context)
                    }
                }
            }
            return sInstance
        }

        private fun createInstance(context: Context) =
            Room.databaseBuilder(
                context,
                MyDatabase::class.java,
                DATA_BASE_NAME
            ).build()
    }

}