package com.runacombo.data.db.dao

import androidx.room.*
import com.runacombo.data.db.entity.InventoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InventoryDao {
    @Query("SELECT * FROM inventory WHERE id = :inventoryId")
    suspend fun getInventoryItem(inventoryId: String): InventoryEntity?

    @Query("SELECT * FROM inventory WHERE playerId = :playerId")
    suspend fun getPlayerInventory(playerId: String): List<InventoryEntity>

    @Query("SELECT * FROM inventory WHERE playerId = :playerId")
    fun getPlayerInventoryFlow(playerId: String): Flow<List<InventoryEntity>>

    @Query("SELECT * FROM inventory WHERE playerId = :playerId AND itemType = :itemType")
    suspend fun getInventoryByType(playerId: String, itemType: String): List<InventoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventoryItem(item: InventoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventoryItems(items: List<InventoryEntity>)

    @Update
    suspend fun updateInventoryItem(item: InventoryEntity)

    @Delete
    suspend fun deleteInventoryItem(item: InventoryEntity)

    @Query("UPDATE inventory SET quantity = quantity + :amount WHERE id = :inventoryId")
    suspend fun addQuantity(inventoryId: String, amount: Int)

    @Query("DELETE FROM inventory WHERE playerId = :playerId AND itemId = :itemId")
    suspend fun removeItem(playerId: String, itemId: Int)
}
