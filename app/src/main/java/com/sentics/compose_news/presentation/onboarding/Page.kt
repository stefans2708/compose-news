package com.sentics.compose_news.presentation.onboarding

import androidx.annotation.DrawableRes
import com.sentics.compose_news.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
    val previousButton: String,
    val nextButton: String
)
