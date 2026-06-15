package com.runacombo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "inventory")
data class InventoryEntity(
    @PrimaryKey
    val id: String,
    val playerId: String,
    val itemId: Int,
    val itemType: String, // RUNE, HERO_CARD, BOOSTER, etc.
    val quantity: Int = 1,
    val addedAt: Long = System.currentTimeMillis()
)
