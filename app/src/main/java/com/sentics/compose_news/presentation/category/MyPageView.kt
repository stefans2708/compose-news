package com.sentics.compose_news.presentation.category

import com.sentics.compose_news.domain.model.MyPage

data class MyPageView<T>(
    val pageIndex: Int,
    val totalCount: Int,
    val items: List<T>
)

fun <DT, PT> MyPage<DT>.toMyPageView(itemMapper: (DT) -> PT) =
    MyPageView<PT>(
        pageIndex = page,
        totalCount = totalCount,
        items = items.map(itemMapper)
    )