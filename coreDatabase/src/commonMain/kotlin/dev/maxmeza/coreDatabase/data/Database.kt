@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package dev.maxmeza.coreDatabase.data

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import dev.maxmeza.coreDatabase.data.model.DeckEntity
import dev.maxmeza.coreDatabase.data.model.FlashcardEntity
import dev.maxmeza.coreDatabase.data.model.TagEntity
import dev.maxmeza.coreDatabase.data.model.FlashcardTagCrossRef
import kotlinx.coroutines.Dispatchers

@Database(
    entities = [
        DeckEntity::class,
        FlashcardEntity::class,
        TagEntity::class,
        FlashcardTagCrossRef::class
    ],
    version = 2
)
@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getDao(): FlashcardDao
}

// The Room compiler generates the `actual` implementations.
@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}