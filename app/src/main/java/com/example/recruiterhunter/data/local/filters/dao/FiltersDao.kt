package com.example.recruiterhunter.data.local.filters.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.recruiterhunter.data.local.filters.entity.FiltersEntity
import kotlinx.coroutines.flow.Flow

interface FiltersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: FiltersEntity)

    @Query("SELECT * FROM filters WHERE id = 1")
    fun getFilters(): Flow<FiltersEntity>

    @Update
    suspend fun update(entity: FiltersEntity)
}