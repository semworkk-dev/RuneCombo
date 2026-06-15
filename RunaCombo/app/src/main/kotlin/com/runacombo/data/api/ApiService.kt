package com.runacombo.data.api

import retrofit2.http.*

interface ApiService {
    // Player endpoints
    @GET("api/v1/player/{playerId}")
    suspend fun getPlayer(@Path("playerId") playerId: String): PlayerResponse

    @POST("api/v1/player/sync")
    suspend fun syncPlayer(@Body playerData: PlayerSyncRequest): PlayerResponse

    @POST("api/v1/player/save-progress")
    suspend fun saveProgress(@Body progressData: ProgressSaveRequest): ProgressResponse

    // Heroes endpoints
    @GET("api/v1/heroes")
    suspend fun getAllHeroes(): List<HeroResponse>

    @POST("api/v1/heroes/{heroId}/unlock")
    suspend fun unlockHero(@Path("heroId") heroId: Int): HeroResponse

    @POST("api/v1/heroes/{heroId}/level-up")
    suspend fun levelUpHero(@Path("heroId") heroId: Int): HeroResponse

    // Runes endpoints
    @GET("api/v1/runes")
    suspend fun getAllRunes(): List<RuneResponse>

    @POST("api/v1/runes/{runeId}/unlock")
    suspend fun unlockRune(@Path("runeId") runeId: Int): RuneResponse

    // Levels endpoints
    @GET("api/v1/levels/{levelId}")
    suspend fun getLevel(@Path("levelId") levelId: Int): LevelResponse

    @POST("api/v1/levels/{levelId}/complete")
    suspend fun completeLevel(
        @Path("levelId") levelId: Int,
        @Body completionData: LevelCompletionRequest
    ): LevelResponse

    // Bosses endpoints
    @GET("api/v1/bosses/{bossId}")
    suspend fun getBoss(@Path("bossId") bossId: Int): BossResponse

    @POST("api/v1/bosses/{bossId}/defeat")
    suspend fun defeatBoss(@Path("bossId") bossId: Int): BossResponse

    // Locations endpoints
    @GET("api/v1/locations")
    suspend fun getAllLocations(): List<LocationResponse>

    @POST("api/v1/locations/{locationId}/unlock")
    suspend fun unlockLocation(@Path("locationId") locationId: Int): LocationResponse
}

// Request/Response DTOs
data class PlayerResponse(
    val id: String,
    val nickname: String,
    val level: Int,
    val coins: Long,
    val crystals: Long,
    val energy: Int,
    val totalWins: Int,
    val totalLosses: Int
)

data class PlayerSyncRequest(
    val playerId: String,
    val level: Int,
    val coins: Long,
    val crystals: Long,
    val totalWins: Int,
    val totalLosses: Int,
    val unlockedHeroes: List<Int>,
    val unlockedRunes: List<Int>
)

data class ProgressSaveRequest(
    val playerId: String,
    val levelId: Int,
    val score: Int,
    val stars: Int,
    val timestamp: Long
)

data class ProgressResponse(
    val success: Boolean,
    val message: String
)

data class HeroResponse(
    val id: Int,
    val name: String,
    val description: String,
    val rarity: Int,
    val health: Int,
    val attack: Int,
    val defense: Int,
    val speed: Int
)

data class RuneResponse(
    val id: Int,
    val name: String,
    val type: String,
    val rarity: Int,
    val damage: Int,
    val effect: String,
    val color: String
)

data class LevelResponse(
    val id: Int,
    val locationId: Int,
    val levelNumber: Int,
    val difficulty: Int,
    val targetScore: Int,
    val movesLimit: Int,
    val isCompleted: Boolean,
    val bestScore: Int,
    val stars: Int
)

data class LevelCompletionRequest(
    val score: Int,
    val stars: Int,
    val timestamp: Long
)

data class BossResponse(
    val id: Int,
    val name: String,
    val description: String,
    val health: Int,
    val attack: Int,
    val defense: Int,
    val isDefeated: Boolean
)

data class LocationResponse(
    val id: Int,
    val name: String,
    val description: String,
    val levelCount: Int,
    val completedLevels: Int,
    val isUnlocked: Boolean
)
