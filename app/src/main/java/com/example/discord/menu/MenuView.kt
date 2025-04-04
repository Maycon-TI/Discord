package com.example.discord.menu

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.discord.data.entities.User

@Composable
fun MenuView(
    navController: NavController,
    user: User?
){
    Text("ID: ${user?.id}")
}