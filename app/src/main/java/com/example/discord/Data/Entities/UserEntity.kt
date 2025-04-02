package com.example.discord.Data.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    var email: String,
    var displayName: String,
    var username: String,
    var password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}