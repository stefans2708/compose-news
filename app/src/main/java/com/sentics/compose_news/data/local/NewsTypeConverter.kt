package com.sentics.compose_news.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sentics.compose_news.domain.model.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String =
        "${source.id},${source.name}"

    @TypeConverter
    fun stringToSource(source: String): Source =
        source.split(",")
            .let { paramArray -> Source(paramArray[0], paramArray[1]) }
}
