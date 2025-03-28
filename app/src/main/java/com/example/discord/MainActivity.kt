package com.example.discord

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.discord.Data.Entities.UserEntity
import com.example.discord.Data.MyApplication
import com.example.discord.ui.theme.DiscordTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userEntity = UserEntity("Douglas", "1", "2", "3")
        MyApplication.database?.userDao()?.insertUser(userEntity)

        println()

        enableEdgeToEdge()
        setContent {
            DiscordTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}

