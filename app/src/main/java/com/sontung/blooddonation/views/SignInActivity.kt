package com.sontung.blooddonation.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.sontung.blooddonation.R
import com.sontung.blooddonation.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySignInBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        
        binding.signupCta.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            
            startActivity(intent)
        }
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}