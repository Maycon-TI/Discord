package com.example.discord

sealed class Screen(val route: String) {
    data object logInView: Screen(route = "login_view")
    data object registerView: Screen(route = "register_view")
}
