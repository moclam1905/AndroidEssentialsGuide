package com.nguyenmoclam.androidessentialsguide.data.local

import androidx.room.TypeConverter

class StringListConverter {
    companion object {
        private const val DELIMITER = ","
    }

    @TypeConverter
    fun fromString(value: String): List<String> {
        return value.split(DELIMITER)
    }

    @TypeConverter
    fun stringListToString(list: List<String>): String {
        return list.joinToString(DELIMITER)
    }
}
