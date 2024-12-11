package com.sontung.blooddonation.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest

class UserRepository (private val context: Context) {
    
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = null
    
    private fun signUpUserWithEmailAndPassword(email: String, password: String, displayName: String) {
        if (email.isNotEmpty() && password.isNotEmpty() && displayName.isNotEmpty()) {
            firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task ->
                        currentUser = firebaseAuth.currentUser
                       
                        val customizedProfile =
                            UserProfileChangeRequest.Builder()
                                .setDisplayName(displayName)
                                .build()
                        
                        currentUser
                            ?.updateProfile(customizedProfile)
                            ?.addOnCompleteListener {
                                updateProfileTask ->
                                    if (!updateProfileTask.isSuccessful) {
                                        Log.d("REGISTER", "Register with display name failed")
                                    }
                            }
                        
                        if (task.isSuccessful) {
                            Toast.makeText(context, "Register Status: SUCCESS", Toast.LENGTH_SHORT).show()
                        }
                }
            
                .addOnFailureListener {
                    exception ->
                        Log.d("REGISTER", exception.message ?: "Error")
                        Toast.makeText(context, "Register Status: FAILED", Toast.LENGTH_SHORT).show()
                }
        }
    }
    
}