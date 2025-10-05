package com.example.recruiterhunter.infrastructure.local.roomdb.vacany.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.entity.VacancyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancy(vacancy: VacancyEntity)

    @Query("Delete FROM vacancies WHERE id = :id")
    suspend fun deleteVacancy(id: Long)

    @Query("Select * FROM vacancies ORDER BY additionTime DESC")
    fun getAllVacancies(): Flow<List<VacancyEntity>>

    @Query("SELECT * FROM vacancies WHERE id = :id")
    suspend fun getVacancy(id: Long): VacancyEntity

    @Query("SELECT EXISTS(SELECT 1 FROM vacancies WHERE id = :id)")
    fun isFavorite(id: Long): Boolean
}