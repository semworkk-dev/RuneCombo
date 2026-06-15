package com.runacombo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "levels")
data class LevelEntity(
    @PrimaryKey
    val id: Int,
    val locationId: Int,
    val levelNumber: Int,
    val difficulty: Int, // 1-5
    val enemyId: Int,
    val targetScore: Int,
    val movesLimit: Int,
    val reward: String, // JSON with coins, crystals, runes
    val isCompleted: Boolean = false,
    val bestScore: Int = 0,
    val stars: Int = 0, // 0-3
    val createdAt: Long = System.currentTimeMillis()
)
