package com.sentics.compose_news.presentation.util

import androidx.compose.runtime.MutableState

val Any.TAG get() = this::class.simpleName

fun <T> MutableState<T>.update(getNewValue: (oldValue: T) -> T) {
    this.value = getNewValue(value)
}