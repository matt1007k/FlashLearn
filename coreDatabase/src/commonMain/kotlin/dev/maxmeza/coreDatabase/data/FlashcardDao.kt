package dev.maxmeza.coreDatabase.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.maxmeza.coreDatabase.data.model.FlashcardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {
    @Insert
    suspend fun insert(item: FlashcardEntity)

    @Query("SELECT count(*) FROM FlashcardEntity")
    suspend fun count(): Int

    @Query("SELECT * FROM FlashcardEntity")
    fun getAllAsFlow(): Flow<List<FlashcardEntity>>
}