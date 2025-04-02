package com.example.discord

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.discord.data.entities.User
import com.example.discord.data.MyApplication
import com.example.discord.ui.theme.DiscordTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val user = User("teste", "a", "b", "c")
        val userDao = MyApplication.database?.userDao()

        //userDao?.insertUser(user)
        userDao?.deleteUserById(5)

        enableEdgeToEdge()
        setContent {
            DiscordTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}

