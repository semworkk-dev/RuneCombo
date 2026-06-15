package com.runacombo.data.db.dao

import androidx.room.*
import com.runacombo.data.db.entity.LevelEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDao {
    @Query("SELECT * FROM levels WHERE id = :levelId")
    suspend fun getLevel(levelId: Int): LevelEntity?

    @Query("SELECT * FROM levels WHERE locationId = :locationId ORDER BY levelNumber ASC")
    suspend fun getLevelsByLocation(locationId: Int): List<LevelEntity>

    @Query("SELECT * FROM levels WHERE locationId = :locationId ORDER BY levelNumber ASC")
    fun getLevelsByLocationFlow(locationId: Int): Flow<List<LevelEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLevel(level: LevelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLevels(levels: List<LevelEntity>)

    @Update
    suspend fun updateLevel(level: LevelEntity)

    @Delete
    suspend fun deleteLevel(level: LevelEntity)

    @Query("UPDATE levels SET isCompleted = 1, bestScore = :score, stars = :stars WHERE id = :levelId")
    suspend fun completeLevel(levelId: Int, score: Int, stars: Int)
}
