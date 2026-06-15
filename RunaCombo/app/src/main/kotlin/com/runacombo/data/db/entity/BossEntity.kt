package com.runacombo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bosses")
data class BossEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val locationId: Int,
    val health: Int,
    val attack: Int,
    val defense: Int,
    val specialAbility: String, // JSON ability data
    val reward: String, // JSON with coins, crystals, runes
    val iconUrl: String = "",
    val isDefeated: Boolean = false,
    val defeatedAt: Long = 0,
    val createdAt: Long = System.currentTimeMillis()
)
