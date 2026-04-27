package com.yuriivaidovych.eggressapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                // Екран Входу
                composable(route = Screen.Login.route) {
                    LoginScreen(
                        onLoginSuccess = {
                            // При успішному вході перекидаємо на Dashboard і видаляємо Login з історії
                            navController.navigate(Screen.Dashboard.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        },
                        onNavigateToRegister = {
                            // Переходимо на екран реєстрації
                            navController.navigate(Screen.Registration.route)
                        }
                    )
                }

                // Екран Реєстрації
                composable(route = Screen.Registration.route) {
                    RegistrationScreen(
                        onRegisterSuccess = {
                            // При успішній реєстрації перекидаємо на Dashboard і чистимо історію
                            navController.navigate(Screen.Dashboard.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        },
                        onNavigateToLogin = {
                            // Просто повертаємося назад на екран входу
                            navController.popBackStack()
                        }
                    )
                }

                // Головний екран
                composable(route = Screen.Dashboard.route) {
                    DashboardScreen()
                }
            }
        }
    }
}