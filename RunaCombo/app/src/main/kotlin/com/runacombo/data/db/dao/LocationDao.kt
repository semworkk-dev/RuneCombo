package com.runacombo.data.db.dao

import androidx.room.*
import com.runacombo.data.db.entity.LocationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Query("SELECT * FROM locations WHERE id = :locationId")
    suspend fun getLocation(locationId: Int): LocationEntity?

    @Query("SELECT * FROM locations")
    suspend fun getAllLocations(): List<LocationEntity>

    @Query("SELECT * FROM locations")
    fun getAllLocationsFlow(): Flow<List<LocationEntity>>

    @Query("SELECT * FROM locations WHERE isUnlocked = 1 ORDER BY id ASC")
    suspend fun getUnlockedLocations(): List<LocationEntity>

    @Query("SELECT * FROM locations WHERE isUnlocked = 1 ORDER BY id ASC")
    fun getUnlockedLocationsFlow(): Flow<List<LocationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocations(locations: List<LocationEntity>)

    @Update
    suspend fun updateLocation(location: LocationEntity)

    @Delete
    suspend fun deleteLocation(location: LocationEntity)

    @Query("UPDATE locations SET isUnlocked = 1, unlockedAt = :timestamp WHERE id = :locationId")
    suspend fun unlockLocation(locationId: Int, timestamp: Long)
}
