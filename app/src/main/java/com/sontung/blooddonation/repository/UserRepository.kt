package com.sontung.blooddonation.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.sontung.blooddonation.model.User
import com.sontung.blooddonation.shared.Paths
import com.sontung.blooddonation.views.DashboardActivity
import com.sontung.blooddonation.views.SignInActivity

class UserRepository (private val context: Context) {

    // FirebaseAuth
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: FirebaseUser? = null

    // Firebase FireStore
    private val db = FirebaseFirestore.getInstance()


    fun signUpUserWithEmailAndPassword (
        email: String,
        password: String,
        displayName: String,
        bloodType: String
    ) {
        if (
            email.isNotEmpty() &&
            password.isNotEmpty() &&
            displayName.isNotEmpty() &&
            bloodType.isNotEmpty()
            ) {
            firebaseAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task ->
                        currentUser = firebaseAuth.currentUser
                       
                        val customizedProfile =
                            UserProfileChangeRequest.Builder()
                                .setDisplayName(displayName)
                                .build()

                    if (currentUser != null) {
                        currentUser!!
                            .updateProfile(customizedProfile)
                            .addOnCompleteListener {
                                    updateProfileTask ->
                                if (!updateProfileTask.isSuccessful) {
                                    Log.d("REGISTER", "Register with display name failed")
                                }
                            }

                        if (task.isSuccessful) {
                            val currentUserId = currentUser!!.uid

                            val userObject = User (
                                currentUserId,
                                displayName,
                                email,
                                password,
                                bloodType
                            )

                            db
                                .collection(Paths.USER_COLLECTION_PATH)
                                .document(currentUserId)
                                .set(userObject)

                            val intent = Intent(context, SignInActivity::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            context.startActivity(intent)

                            Toast.makeText(context, "Register Status: SUCCESS", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            
                .addOnFailureListener {
                    exception ->
                        Log.d("REGISTER", exception.message ?: "Error")
                        Toast.makeText(context, "Register Status: FAILED", Toast.LENGTH_SHORT).show()
                }
        }
    }

    fun signInUserWithEmailAndPassword (
         email: String, password: String
    ) {
        if (
            email.isNotEmpty() &&
            password.isNotEmpty()
        ) {
            firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task ->
                        if(task.isSuccessful) {
                            Toast.makeText(context, "Login Status: SUCCESS", Toast.LENGTH_SHORT).show()

                            val intent = Intent(context, DashboardActivity::class.java)
                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            context.startActivity(intent)
                        }
                }

                .addOnFailureListener {
                    exception ->
                        Toast.makeText(context, "Login Status: FAILED", Toast.LENGTH_SHORT).show()
                        Log.d("LOGIN", exception.message ?: "Error")
                }
        }
    }
}