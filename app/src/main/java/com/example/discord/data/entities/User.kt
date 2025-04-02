package com.example.discord.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    var email: String,
    var displayName: String,
    var username: String,
    var password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}