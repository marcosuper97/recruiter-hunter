package com.example.recruiterhunter.infrastructure.local.roomdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.recruiterhunter.infrastructure.local.roomdb.filters.dao.FiltersDao
import com.example.recruiterhunter.infrastructure.local.roomdb.filters.entity.FiltersEntity
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.dao.VacancyDao
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.entity.VacancyEntity
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.entity.type_converter.JsonConverter

@Database(
    version = 1,
    entities = [VacancyEntity::class, FiltersEntity::class]
)
@TypeConverters(JsonConverter::class)
abstract class AppDb() : RoomDatabase() {
    abstract fun vacanciesDao(): VacancyDao
    abstract fun filtersDao(): FiltersDao
}