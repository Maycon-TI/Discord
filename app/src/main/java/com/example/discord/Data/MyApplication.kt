package com.example.discord.Data

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

open class MyApplication: Application() {


    companion object {
        var database: AppDatabase? = null
    }


    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext,
            AppDatabase::class.java, "my-db")
            .allowMainThreadQueries()
            .build()
    }
}
