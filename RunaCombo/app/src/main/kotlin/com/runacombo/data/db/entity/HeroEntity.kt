package com.runacombo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "heroes")
data class HeroEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val rarity: Int, // 1-5 stars
    val level: Int = 1,
    val experience: Long = 0,
    val health: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int,
    val ability: String, // JSON ability data
    val iconUrl: String = "",
    val isUnlocked: Boolean = false,
    val equippedRunes: String = "", // JSON list of rune IDs
    val createdAt: Long = System.currentTimeMillis()
)
