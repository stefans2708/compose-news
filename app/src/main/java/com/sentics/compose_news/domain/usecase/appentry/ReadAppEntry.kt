package com.sentics.compose_news.domain.usecase.appentry

import com.sentics.compose_news.domain.user.LocalUserManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAppEntry @Inject constructor(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean> =
        localUserManager.readAppEntry()
}
