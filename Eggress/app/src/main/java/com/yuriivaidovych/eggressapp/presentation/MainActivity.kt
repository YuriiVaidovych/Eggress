package com.yuriivaidovych.eggressapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.yuriivaidovych.eggressapp.presentation.navigation.LoginScreen
import com.yuriivaidovych.eggressapp.presentation.navigation.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: MainViewModel = hiltViewModel()

            val navController = rememberNavController()

            val startDestination = if (viewModel.isUserSignedIn) {
                Screen.Dashboard.route
            } else {
                Screen.Login.route
            }

            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable(route = Screen.Login.route) {
                    LoginScreen()
                }
                composable(route = Screen.Dashboard.route) {
                    DashboardScreen()
                }
            }
        }
    }
}