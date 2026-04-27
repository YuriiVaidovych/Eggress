package com.yuriivaidovych.eggressapp.data

import com.google.firebase.auth.FirebaseAuth
import com.yuriivaidovych.eggressapp.domain.AuthRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) { false }
    }

    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) { false }
    }

    override fun signOut() = firebaseAuth.signOut()

    override fun isUserSignedIn(): Boolean = firebaseAuth.currentUser != null
}