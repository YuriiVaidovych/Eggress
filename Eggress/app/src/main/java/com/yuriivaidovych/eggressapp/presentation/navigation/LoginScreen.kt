package com.yuriivaidovych.eggressapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: AuthViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Eggress", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Вхід у систему", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = viewModel.email.value,
            onValueChange = { viewModel.email.value = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.password.value,
            onValueChange = { viewModel.password.value = it },
            label = { Text("Пароль") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        // Виведення помилки, якщо вона є
        viewModel.errorMessage.value?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка "Забули пароль" (поки без логіки)
        TextButton(
            onClick = { /* TODO: Forgot Password logic */ },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Забули пароль?")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (viewModel.isLoading.value) {
            CircularProgressIndicator()
        } else {
            Button(
                onClick = { viewModel.login(onLoginSuccess) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Увійти")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onNavigateToRegister) {
            Text("Немає акаунту? Зареєструватися")
        }
    }
}