package com.example.discord

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.discord.data.MyApplication

import com.example.discord.login.LogInView
import com.example.discord.menu.MenuView
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
        composable(
            route = Screen.menuView.route + "/{idUser}",
            arguments = listOf(navArgument("idUser") { type = NavType.StringType })
        ) {
            val idUser = it.arguments?.getString("idUser") ?: ""
            val user = MyApplication.database?.userDao()?.findUserById(idUser.toInt())
            MenuView(navController = navController, user)
        }
    }
}
