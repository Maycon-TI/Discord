package com.example.discord.Data

import android.app.Application
import androidx.room.Room

open class MyApplication: Application() {


    companion object {
        var database: AppDatabase? = null
    }


    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "my-db").allowMainThreadQueries().build()
    }
}
