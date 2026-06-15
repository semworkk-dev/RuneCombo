package com.runacombo.data.db.dao

import androidx.room.*
import com.runacombo.data.db.entity.BossEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BossDao {
    @Query("SELECT * FROM bosses WHERE id = :bossId")
    suspend fun getBoss(bossId: Int): BossEntity?

    @Query("SELECT * FROM bosses WHERE locationId = :locationId")
    suspend fun getBossesByLocation(locationId: Int): List<BossEntity>

    @Query("SELECT * FROM bosses WHERE locationId = :locationId")
    fun getBossesByLocationFlow(locationId: Int): Flow<List<BossEntity>>

    @Query("SELECT * FROM bosses")
    suspend fun getAllBosses(): List<BossEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoss(boss: BossEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBosses(bosses: List<BossEntity>)

    @Update
    suspend fun updateBoss(boss: BossEntity)

    @Delete
    suspend fun deleteBoss(boss: BossEntity)

    @Query("UPDATE bosses SET isDefeated = 1, defeatedAt = :timestamp WHERE id = :bossId")
    suspend fun defeatedBoss(bossId: Int, timestamp: Long)
}
