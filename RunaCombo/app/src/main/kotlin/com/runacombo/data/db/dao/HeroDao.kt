package com.runacombo.data.db.dao

import androidx.room.*
import com.runacombo.data.db.entity.HeroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {
    @Query("SELECT * FROM heroes WHERE id = :heroId")
    suspend fun getHero(heroId: Int): HeroEntity?

    @Query("SELECT * FROM heroes")
    suspend fun getAllHeroes(): List<HeroEntity>

    @Query("SELECT * FROM heroes")
    fun getAllHeroesFlow(): Flow<List<HeroEntity>>

    @Query("SELECT * FROM heroes WHERE isUnlocked = 1")
    suspend fun getUnlockedHeroes(): List<HeroEntity>

    @Query("SELECT * FROM heroes WHERE isUnlocked = 1")
    fun getUnlockedHeroesFlow(): Flow<List<HeroEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHero(hero: HeroEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHeroes(heroes: List<HeroEntity>)

    @Update
    suspend fun updateHero(hero: HeroEntity)

    @Delete
    suspend fun deleteHero(hero: HeroEntity)

    @Query("UPDATE heroes SET isUnlocked = 1 WHERE id = :heroId")
    suspend fun unlockHero(heroId: Int)

    @Query("UPDATE heroes SET level = level + 1 WHERE id = :heroId")
    suspend fun levelUpHero(heroId: Int)
}
