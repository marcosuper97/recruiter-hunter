package com.example.recruiterhunter.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.recruiterhunter.data.local.filters.entity.FiltersEntity
import com.example.recruiterhunter.data.local.vacany.dao.VacancyDao
import com.example.recruiterhunter.data.local.vacany.entity.VacancyEntity

@Database(
    version = 1,
    entities = [VacancyEntity::class, FiltersEntity::class]
)
abstract class AppDb() : RoomDatabase() {
    abstract fun vacanciesDao(): VacancyDao
}