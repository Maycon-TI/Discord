package com.example.discord.Data.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.discord.Data.Entities.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM UserEntity WHERE email = :email AND password = :password")
    fun findUserByEmail(email: String, password: String): UserEntity?

    @Query("DELETE FROM UserEntity WHERE id = :userId")
    fun deleteUserById(userId: Int)
}