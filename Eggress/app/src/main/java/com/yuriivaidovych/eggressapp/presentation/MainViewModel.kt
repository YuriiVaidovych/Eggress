package com.yuriivaidovych.eggressapp.presentation

import androidx.lifecycle.ViewModel
import com.yuriivaidovych.eggressapp.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val isUserSignedIn: Boolean = authRepository.isUserSignedIn()
}