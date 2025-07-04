package com.sentics.compose_news.presentation.category

data class CategorySettingsState(
    val language: String = "en",
    val sortingCriteria: String = "relevancy",
    val dateFrom: String? = null,
    val dateTo: String? = null,
    val sources: List<String> = availableSources
)

val CategorySettingsState.availableLanguages
    get() = listOf("ar", "de", "en", "es", "fr", "he", "it", "nl", "no", "pt", "ru", "sv", "ud", "zh")

val CategorySettingsState.availableSortingCriteria
    get() = listOf("relevancy", "popularity", "publishedAt")

val availableSources
    get() = listOf(
        "abc-news",
        "al-jazeera-english",
        "ansa",
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
        "le-monde",
        "mtv-news",
        "rbc",
        "techcrunch",
        "techcrunch-cn",
        "techradar",
        "the-sport-bible",
        "the-verge",
        "time",
        "vice-news"
    )
