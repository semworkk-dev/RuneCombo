package com.runacombo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.runacombo.data.db.dao.*
import com.runacombo.data.db.entity.*

@Database(
    entities = [
        PlayerEntity::class,
        HeroEntity::class,
        RuneEntity::class,
        LevelEntity::class,
        BossEntity::class,
        LocationEntity::class,
        InventoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RunaComboDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun heroDao(): HeroDao
    abstract fun runeDao(): RuneDao
    abstract fun levelDao(): LevelDao
    abstract fun bossDao(): BossDao
    abstract fun locationDao(): LocationDao
    abstract fun inventoryDao(): InventoryDao

    companion object {
        const val DATABASE_NAME = "runa_combo_db"
    }
}
