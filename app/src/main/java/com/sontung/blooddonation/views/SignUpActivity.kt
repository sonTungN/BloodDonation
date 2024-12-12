package com.sontung.blooddonation.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.google.firebase.FirebaseApp
import com.sontung.blooddonation.R
import com.sontung.blooddonation.databinding.ActivitySignUpBinding
import com.sontung.blooddonation.repository.UserRepository
import com.sontung.blooddonation.viewmodel.UserViewModel

class SignUpActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var userViewModel: UserViewModel

    // Widget
    private lateinit var bloodTypeSpinner: Spinner
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        userViewModel = UserViewModel(this)

        setUpBloodTypeSpinner()
        setUpButtonClickHandlerEvent()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setUpBloodTypeSpinner() {
        bloodTypeSpinner = binding.signupBloodType
        val bloodTypesAdapter = ArrayAdapter.createFromResource (
            this,
            R.array.blood_types,
            android.R.layout.simple_spinner_item
        )
        bloodTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bloodTypeSpinner.adapter = bloodTypesAdapter

        // Default selection for spinner
        bloodTypeSpinner.setSelection(0)
    }

    private fun setUpButtonClickHandlerEvent() {
        binding.signupBtn.setOnClickListener {
            val email = binding.signupEmail.text.toString().trim()
            val password = binding.signupPassword.text.toString().trim()
            val displayName = binding.signupDisplayName.text.toString().trim()
            val bloodType = bloodTypeSpinner.selectedItem.toString()

            userViewModel.signUpUserWithEmailAndPassword(email, password, displayName, bloodType)
        }

        binding.signinCta.setOnClickListener {
            navigateToSignInActivity()
        }
    }

    private fun navigateToSignInActivity() {
        val intent = Intent(this, SignInActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

        startActivity(intent)
    }
}