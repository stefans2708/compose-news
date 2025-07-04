package com.sentics.compose_news.presentation.category

import com.sentics.compose_news.util.Constant

data class CategorySettingsState(
    val language: String = "",
    val sortingCriteria: String = "",
    val sources: List<String> = Constant.NEWS_SOURCES
) {
    val availableLanguages
        get() = listOf("ar", "de", "en", "es", "fr", "he", "it", "nl", "no", "pt", "ru", "sv", "ud", "zh")

    val availableSortingCriteria
        get() = listOf("relevancy", "popularity", "publishedAt")

    val availableSources
        get() = listOf(
            "abc-news",
            "al-jazeera-english",
            "bbc-news",
            "bbc-sport",
            "bloomberg",
            "cnn",
            "el-mundo",
            "entertainment-weekly",
            "financial-post",
            "fox-news",
            "google-news",
            "hacker-news",
            "mtv-news",
            "techcrunch",
            "techradar",
            "the-sport-bible",
            "the-verge",
            "time",
            "vice-news"
        )
}