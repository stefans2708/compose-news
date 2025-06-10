package com.sentics.compose_news.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sentics.compose_news.R
import com.sentics.compose_news.domain.usecase.appentry.SaveAppEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveAppEntry: SaveAppEntry
) : ViewModel() {

    val pages = listOf(
        Page(
            title = "Lorem Ipsum is simply dummy",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
            image = R.drawable.onboarding1,
            previousButton = "",
            nextButton = "Next"
        ),
        Page(
            title = "Lorem Ipsum is simply dummy",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
            image = R.drawable.onboarding2,
            previousButton = "Go Back",
            nextButton = "Next"
        ),
        Page(
            title = "Lorem Ipsum is simply dummy",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry",
            image = R.drawable.onboarding3,
            previousButton = "Go Back",
            nextButton = "Get Started"
        ),
    )

    fun onEvent(event: OnboardingEvent) {
        when (event) {
            is OnboardingEvent.SaveAppEntry -> saveAppEntry()
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            saveAppEntry.invoke()
        }
    }
}
