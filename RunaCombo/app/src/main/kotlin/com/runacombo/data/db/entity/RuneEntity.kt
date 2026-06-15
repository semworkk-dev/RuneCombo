package com.runacombo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "runes")
data class RuneEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String, // FIRE, WATER, NATURE, LIGHTNING, LIGHT, SHADOW
    val rarity: Int, // 1-5 stars
    val damage: Int,
    val effect: String, // Special effect description
    val color: String, // Hex color
    val iconUrl: String = "",
    val isUnlocked: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
