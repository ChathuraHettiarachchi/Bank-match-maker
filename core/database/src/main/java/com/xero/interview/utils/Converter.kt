package com.xero.interview.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// type converters to save data as lists on db
class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<Long>? {
        val listType = object : TypeToken<List<Long>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toString(value: List<Long>?): String? {
        return gson.toJson(value)
    }
}