package com.example.discord.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.discord.Data.Entities.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: User)

    @Query("DELETE FROM User WHERE id = :userId")
    fun deleteUserById(userId: Int)
}