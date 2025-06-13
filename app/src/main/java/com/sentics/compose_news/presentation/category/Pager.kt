package com.sentics.compose_news.presentation.category

import com.sentics.compose_news.domain.model.PageConfig
import javax.inject.Inject

class Pager @Inject constructor() {

    private var loader: (suspend (PageConfig) -> Unit)? = null
    private var config: PageConfig = PageConfig()

    fun setup(loader: suspend (PageConfig) -> Unit) {
        this.loader = loader
    }

    suspend fun nextPage() {
        if (config.isLoading) return

        loader?.invoke(config)

        config.isLoading = false
        config.pageToLoad++
    }

    fun reset() {
        config = PageConfig()
    }
}