package com.sontung.blooddonation.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.sontung.blooddonation.R
import com.sontung.blooddonation.adapter.OnBoardingItemAdapter
import com.sontung.blooddonation.databinding.ActivityOnBoardingBinding

class OnBoardingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding
    private lateinit var viewPager2: ViewPager2
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_on_boarding)
        
        binding = DataBindingUtil.setContentView(this, R.layout.activity_on_boarding)
        viewPager2 = binding.viewPager2
        
        val onboardPageItems = listOf(
            OnBoardingItem (
                "Find Donors",
                "Instantly connect with verified donors nearby, reducing wait times and securing essential blood donations.",
                R.drawable.onboarding_1
            ),
            OnBoardingItem (
                "Testing",
                "Quickly schedule health checks, reducing delays and ensuring readiness for confident, hassle-free donations.",
                R.drawable.onboarding_2
            ),
            OnBoardingItem (
                "Donation Tracking",
                "Track donations, set reminders, and monitor impact, keeping the donation process organized and meaningful.",
                R.drawable.onboarding_3
            ),
            OnBoardingItem (
                "Community Impact",
                "Join a compassionate donor community, inspiring generosity and strengthening life-saving networks.",
                R.drawable.onboarding_4
            )
        )
        
        binding.viewPager2.adapter = OnBoardingItemAdapter(onboardPageItems)
        
        // Define and Setup Dot Indicators instead of Tab Layout
        val indicator = binding.dotsIndicator
        indicator.attachTo(viewPager2)
        
        binding.viewPager2.registerOnPageChangeCallback (
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val isLast = position == onboardPageItems.lastIndex
                    
                    binding.beginBtn.text = if (isLast) "Let's Begin" else "Next"
                }
            }
        )
        
        binding.beginBtn.setOnClickListener {
            val currentPage = viewPager2.currentItem
            if (currentPage < onboardPageItems.size - 1) {
                viewPager2.currentItem += 1
                
            } else {
                navigateToUserSignIn()
            }
        }
        
        binding.skipBtn.setOnClickListener {
            navigateToUserSignIn()
        }
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    
    private fun navigateToUserSignIn() {
        val intent = Intent(this@OnBoardingActivity, SignInActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        
        startActivity(intent)
        finish()
    }
}

data class OnBoardingItem (
    val title: String,
    val description: String,
    val imageResId: Int
)