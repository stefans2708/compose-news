package com.sentics.compose_news.domain.usecase.appentry

import com.sentics.compose_news.domain.user.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}
