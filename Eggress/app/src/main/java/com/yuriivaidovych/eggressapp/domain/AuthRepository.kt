package com.yuriivaidovych.eggressapp.domain

interface AuthRepository {
    suspend fun signUp(email: String, password: String): Boolean
    suspend fun signIn(email: String, password: String): Boolean
    fun signOut()
    fun isUserSignedIn(): Boolean
}