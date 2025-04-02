package com.example.discord.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.discord.data.entities.User

@Dao
interface IUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Query("SELECT * FROM User WHERE email = :email AND password = :password")
    fun findUserByEmail(email: String, password: String): User?

    @Query("DELETE FROM User WHERE id = :userId")
    fun deleteUserById(userId: Int)
}