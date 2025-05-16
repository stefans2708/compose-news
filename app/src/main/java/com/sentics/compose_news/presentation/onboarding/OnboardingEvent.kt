package com.sentics.compose_news.presentation.onboarding

sealed class OnboardingEvent {

    data object SaveAppEntry : OnboardingEvent()
}