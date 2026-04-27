package com.yuriivaidovych.eggressapp.presentation.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Registration : Screen("registration")
    object Dashboard : Screen("dashboard")
}