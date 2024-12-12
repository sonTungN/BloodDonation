package com.sontung.blooddonation.viewmodel

import android.content.Context
import com.sontung.blooddonation.repository.UserRepository

class UserViewModel (
    private val context: Context
) {
    private val userRepository = UserRepository(context)

    fun signUpUserWithEmailAndPassword(
        email: String,
        password: String,
        displayName: String,
        bloodType: String
    ) {
        userRepository.signUpUserWithEmailAndPassword(email, password, displayName, bloodType)
    }

    fun signInUserWithEmailAndPassword(
        email: String,
        password: String
    ) {
        userRepository.signInUserWithEmailAndPassword(email, password)
    }
}