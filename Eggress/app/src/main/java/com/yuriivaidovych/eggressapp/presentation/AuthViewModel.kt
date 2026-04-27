package com.yuriivaidovych.eggressapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yuriivaidovych.eggressapp.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var email = mutableStateOf("")
    var password = mutableStateOf("")

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val isSuccess = authRepository.signIn(email.value, password.value)

            _isLoading.value = false
            if (isSuccess) {
                onSuccess()
            } else {
                _errorMessage.value = "Помилка входу: невірний email або пароль"
            }
        }
    }

    fun register(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            val isSuccess = authRepository.signUp(email.value, password.value)

            _isLoading.value = false
            if (isSuccess) {
                onSuccess()
            } else {
                _errorMessage.value = "Помилка: не вдалося створити акаунт"
            }
        }
    }
}