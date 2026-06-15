package com.runacombo.data.db.dao

import androidx.room.*
import com.runacombo.data.db.entity.PlayerEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM players WHERE id = :playerId")
    suspend fun getPlayer(playerId: String): PlayerEntity?

    @Query("SELECT * FROM players WHERE id = :playerId")
    fun getPlayerFlow(playerId: String): Flow<PlayerEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: PlayerEntity)

    @Update
    suspend fun updatePlayer(player: PlayerEntity)

    @Delete
    suspend fun deletePlayer(player: PlayerEntity)

    @Query("UPDATE players SET coins = coins + :amount WHERE id = :playerId")
    suspend fun addCoins(playerId: String, amount: Long)

    @Query("UPDATE players SET crystals = crystals + :amount WHERE id = :playerId")
    suspend fun addCrystals(playerId: String, amount: Long)

    @Query("UPDATE players SET energy = :energy WHERE id = :playerId")
    suspend fun updateEnergy(playerId: String, energy: Int)

    @Query("UPDATE players SET level = level + 1, experience = 0 WHERE id = :playerId")
    suspend fun levelUp(playerId: String)
}
