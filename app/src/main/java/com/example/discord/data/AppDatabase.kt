package com.example.discord.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.discord.data.dao.IUserDao
import com.example.discord.data.entities.User

@Database(version = 1, entities = [User::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): IUserDao
}