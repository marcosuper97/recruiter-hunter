package com.example.recruiterhunter.data.local.vacany.entity.type_converter

import androidx.room.TypeConverter
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json

class JsonConverter {
    @TypeConverter
    fun decodeToJson(listString: List<String>): String {
        return Json.encodeToString(
            serializer = ListSerializer(String.serializer()),
            value = listString
        )
    }

    @TypeConverter
    fun decodeToList(jsonString: String): List<String> {
        return Json.decodeFromString(
            deserializer = ListSerializer(String.serializer()),
            string = jsonString
        )
    }
}