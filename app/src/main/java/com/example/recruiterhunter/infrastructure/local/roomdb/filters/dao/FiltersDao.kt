package com.example.recruiterhunter.infrastructure.local.roomdb.filters.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.recruiterhunter.infrastructure.local.roomdb.filters.entity.FiltersEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FiltersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: FiltersEntity)

    @Query("SELECT * FROM filters WHERE id = 1")
    fun getFilters(): Flow<FiltersEntity?>

    @Update
    suspend fun update(entity: FiltersEntity)

    @Query(
        """
        SELECT 
            CASE 
                WHEN country IS NOT NULL OR 
                     countryId IS NOT NULL OR 
                     area IS NOT NULL OR 
                     areaId IS NOT NULL OR 
                     industry IS NOT NULL OR 
                     industryId IS NOT NULL OR 
                     salary IS NOT NULL OR 
                     onlyWithSalary IS NOT NULL 
                THEN 1 
                ELSE 0 
            END as has_filters
        FROM filters
        WHERE id = 0
    """
    )
    fun hasAnyFilters(): Flow<Boolean>
}