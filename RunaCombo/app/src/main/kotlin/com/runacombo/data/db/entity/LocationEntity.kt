package com.runacombo.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locations")
data class LocationEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val backgroundUrl: String = "",
    val levelCount: Int,
    val completedLevels: Int = 0,
    val isUnlocked: Boolean = false,
    val unlockedAt: Long = 0,
    val createdAt: Long = System.currentTimeMillis()
)
