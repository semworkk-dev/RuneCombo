package com.runacombo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class PlayerEntity(
    @PrimaryKey
    val id: String = "player_1",
    val nickname: String = "Игрок",
    val level: Int = 1,
    val experience: Long = 0,
    val coins: Long = 0,
    val crystals: Long = 0,
    val energy: Int = 30,
    val maxEnergy: Int = 30,
    val lastEnergyRefillTime: Long = 0,
    val totalWins: Int = 0,
    val totalLosses: Int = 0,
    val currentLocationId: Int = 1,
    val currentLevelId: Int = 1,
    val unlockedHeroes: String = "1,2", // JSON list of hero IDs
    val unlockedRunes: String = "1,2,3,4,5,6", // JSON list of rune IDs
    val createdAt: Long = System.currentTimeMillis(),
    val lastSyncTime: Long = 0
)
