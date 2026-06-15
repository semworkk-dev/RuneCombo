package com.runacombo.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String?): List<String> {
        return if (value == null) {
            emptyList()
        } else {
            val listType = object : TypeToken<List<String>>() {}.type
            gson.fromJson(value, listType)
        }
    }

    @TypeConverter
    fun toStringList(list: List<String>?): String {
        return gson.toJson(list ?: emptyList())
    }

    @TypeConverter
    fun fromIntList(value: String?): List<Int> {
        return if (value == null) {
            emptyList()
        } else {
            val listType = object : TypeToken<List<Int>>() {}.type
            gson.fromJson(value, listType)
        }
    }

    @TypeConverter
    fun toIntList(list: List<Int>?): String {
        return gson.toJson(list ?: emptyList())
    }

    @TypeConverter
    fun fromMap(value: String?): Map<String, Int> {
        return if (value == null) {
            emptyMap()
        } else {
            val mapType = object : TypeToken<Map<String, Int>>() {}.type
            gson.fromJson(value, mapType)
        }
    }

    @TypeConverter
    fun toMap(map: Map<String, Int>?): String {
        return gson.toJson(map ?: emptyMap())
    }
}
