package com.sontung.blooddonation.model

import androidx.annotation.DrawableRes

data class OnBoardingItem (
    val title: String,
    val description: String,
    @DrawableRes val imageResId: Int
)