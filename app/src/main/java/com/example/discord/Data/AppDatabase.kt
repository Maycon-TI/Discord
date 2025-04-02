package com.example.discord.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.discord.Data.Dao.UserDao
import com.example.discord.Data.Entities.UserEntity

@Database(version = 6, entities = [UserEntity::class])
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}