package com.example.discord.data

import android.app.Application
import androidx.room.Room

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
