package com.runacombo.data.db.dao

import androidx.room.*
import com.runacombo.data.db.entity.RuneEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RuneDao {
    @Query("SELECT * FROM runes WHERE id = :runeId")
    suspend fun getRune(runeId: Int): RuneEntity?

    @Query("SELECT * FROM runes")
    suspend fun getAllRunes(): List<RuneEntity>

    @Query("SELECT * FROM runes")
    fun getAllRunesFlow(): Flow<List<RuneEntity>>

    @Query("SELECT * FROM runes WHERE isUnlocked = 1")
    suspend fun getUnlockedRunes(): List<RuneEntity>

    @Query("SELECT * FROM runes WHERE isUnlocked = 1")
    fun getUnlockedRunesFlow(): Flow<List<RuneEntity>>

    @Query("SELECT * FROM runes WHERE type = :runeType")
    suspend fun getRunesByType(runeType: String): List<RuneEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRune(rune: RuneEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRunes(runes: List<RuneEntity>)

    @Update
    suspend fun updateRune(rune: RuneEntity)

    @Delete
    suspend fun deleteRune(rune: RuneEntity)

    @Query("UPDATE runes SET isUnlocked = 1 WHERE id = :runeId")
    suspend fun unlockRune(runeId: Int)
}
