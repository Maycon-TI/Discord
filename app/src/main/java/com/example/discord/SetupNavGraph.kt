package com.example.discord

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import com.example.discord.login.LogInView
import com.example.discord.register.RegisterView

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.logInView.route
    ) {
        composable(route = Screen.logInView.route) {
            LogInView(navController = navController)
        }
        composable(route = Screen.registerView.route) {
            RegisterView(navController = navController)
        }
    }
}
