package com.runacombo.data.repository

import com.runacombo.data.api.ApiService
import com.runacombo.data.db.RunaComboDatabase
import com.runacombo.data.db.entity.*
import com.runacombo.domain.model.Hero
import com.runacombo.domain.model.HeroPresets
import com.runacombo.domain.model.Rune
import com.runacombo.domain.model.RuneType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val database: RunaComboDatabase,
    private val apiService: ApiService
) {
    // Player operations
    fun getPlayerFlow(playerId: String): Flow<PlayerEntity?> {
        return database.playerDao().getPlayerFlow(playerId)
    }

    suspend fun getPlayer(playerId: String): PlayerEntity? {
        return database.playerDao().getPlayer(playerId)
    }

    suspend fun createOrUpdatePlayer(player: PlayerEntity) {
        database.playerDao().insertPlayer(player)
    }

    suspend fun addCoins(playerId: String, amount: Long) {
        database.playerDao().addCoins(playerId, amount)
    }

    suspend fun addCrystals(playerId: String, amount: Long) {
        database.playerDao().addCrystals(playerId, amount)
    }

    suspend fun updateEnergy(playerId: String, energy: Int) {
        database.playerDao().updateEnergy(playerId, energy)
    }

    // Hero operations
    fun getUnlockedHeroesFlow(): Flow<List<HeroEntity>> {
        return database.heroDao().getUnlockedHeroesFlow()
    }

    suspend fun getUnlockedHeroes(): List<HeroEntity> {
        return database.heroDao().getUnlockedHeroes()
    }

    suspend fun getAllHeroes(): List<HeroEntity> {
        return database.heroDao().getAllHeroes()
    }

    suspend fun unlockHero(heroId: Int) {
        database.heroDao().unlockHero(heroId)
    }

    suspend fun initializeDefaultHeroes() {
        val heroes = HeroPresets.ALL_HEROES.map { hero ->
            HeroEntity(
                id = hero.id,
                name = hero.name,
                description = hero.description,
                rarity = hero.rarity,
                level = hero.level,
                experience = hero.experience,
                health = hero.health,
                attack = hero.attack,
                defense = hero.defense,
                speed = hero.speed,
                ability = hero.ability.toString(),
                isUnlocked = hero.isUnlocked,
                equippedRunes = ""
            )
        }
        database.heroDao().insertHeroes(heroes)
    }

    // Rune operations
    fun getUnlockedRunesFlow(): Flow<List<RuneEntity>> {
        return database.runeDao().getUnlockedRunesFlow()
    }

    suspend fun getUnlockedRunes(): List<RuneEntity> {
        return database.runeDao().getUnlockedRunes()
    }

    suspend fun getAllRunes(): List<RuneEntity> {
        return database.runeDao().getAllRunes()
    }

    suspend fun unlockRune(runeId: Int) {
        database.runeDao().unlockRune(runeId)
    }

    suspend fun initializeDefaultRunes() {
        val runes = listOf(
            RuneEntity(1, "Огненная руна", "FIRE", 1, 50, "Урон огнем", "#FF6B35"),
            RuneEntity(2, "Ледяная руна", "WATER", 1, 50, "Урон льдом", "#004E89"),
            RuneEntity(3, "Руна природы", "NATURE", 1, 50, "Исцеление", "#1B998B"),
            RuneEntity(4, "Руна молнии", "LIGHTNING", 1, 60, "Урон молнией", "#FFD700"),
            RuneEntity(5, "Руна света", "LIGHT", 2, 70, "Магический урон", "#FFFFFF"),
            RuneEntity(6, "Руна тени", "SHADOW", 2, 80, "Темный урон", "#2D3142")
        )
        database.runeDao().insertRunes(runes)
    }

    // Level operations
    fun getLevelsByLocationFlow(locationId: Int): Flow<List<LevelEntity>> {
        return database.levelDao().getLevelsByLocationFlow(locationId)
    }

    suspend fun getLevelsByLocation(locationId: Int): List<LevelEntity> {
        return database.levelDao().getLevelsByLocation(locationId)
    }

    suspend fun getLevel(levelId: Int): LevelEntity? {
        return database.levelDao().getLevel(levelId)
    }

    suspend fun completeLevel(levelId: Int, score: Int, stars: Int) {
        database.levelDao().completeLevel(levelId, score, stars)
    }

    // Location operations
    fun getUnlockedLocationsFlow(): Flow<List<LocationEntity>> {
        return database.locationDao().getUnlockedLocationsFlow()
    }

    suspend fun getUnlockedLocations(): List<LocationEntity> {
        return database.locationDao().getUnlockedLocations()
    }

    suspend fun getAllLocations(): List<LocationEntity> {
        return database.locationDao().getAllLocations()
    }

    suspend fun unlockLocation(locationId: Int) {
        database.locationDao().unlockLocation(locationId, System.currentTimeMillis())
    }

    suspend fun initializeDefaultLocations() {
        val locations = listOf(
            LocationEntity(1, "Деревня Домового", "Начальная локация", levelCount = 10),
            LocationEntity(2, "Лес Лисы", "Таинственный лес", levelCount = 10),
            LocationEntity(3, "Крепость Богатыря", "Мощная крепость", levelCount = 10),
            LocationEntity(4, "Ярмарочная площадь", "Оживленная площадь", levelCount = 10),
            LocationEntity(5, "Заколдованные болота", "Опасные болота", levelCount = 10),
            LocationEntity(6, "Горы Самоцветов", "Сверкающие горы", levelCount = 10),
            LocationEntity(7, "Ледяной Терем", "Ледяной дворец", levelCount = 10),
            LocationEntity(8, "Башня Кощея", "Финальная локация", levelCount = 20)
        )
        database.locationDao().insertLocations(locations)
    }

    // Boss operations
    suspend fun getBoss(bossId: Int): BossEntity? {
        return database.bossDao().getBoss(bossId)
    }

    suspend fun getBossesByLocation(locationId: Int): List<BossEntity> {
        return database.bossDao().getBossesByLocation(locationId)
    }

    suspend fun defeatBoss(bossId: Int) {
        database.bossDao().defeatedBoss(bossId, System.currentTimeMillis())
    }

    // Sync operations
    suspend fun syncWithServer(playerId: String) {
        try {
            val player = getPlayer(playerId) ?: return
            val heroes = getUnlockedHeroes()
            val runes = getUnlockedRunes()

            val syncRequest = com.runacombo.data.api.PlayerSyncRequest(
                playerId = playerId,
                level = player.level.toInt(),
                coins = player.coins,
                crystals = player.crystals,
                totalWins = player.totalWins,
                totalLosses = player.totalLosses,
                unlockedHeroes = heroes.map { it.id },
                unlockedRunes = runes.map { it.id }
            )

            val response = apiService.syncPlayer(syncRequest)
            // Update local data with server response
            val updatedPlayer = player.copy(
                level = response.level,
                coins = response.coins,
                crystals = response.crystals,
                totalWins = response.totalWins,
                totalLosses = response.totalLosses,
                lastSyncTime = System.currentTimeMillis()
            )
            createOrUpdatePlayer(updatedPlayer)
        } catch (e: Exception) {
            // Handle offline mode - data will sync when connection is restored
            e.printStackTrace()
        }
    }
}
