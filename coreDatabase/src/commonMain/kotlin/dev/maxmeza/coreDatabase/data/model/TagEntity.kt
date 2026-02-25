package dev.maxmeza.coreDatabase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TagEntity(
    @PrimaryKey(autoGenerate = true) val tagId: Long = 0,
    val name: String,
)

@Entity(primaryKeys = ["tagId", "flashcardId"])
data class FlashcardTagCrossRef(
    val tagId: Long,
    val flashcardId: Long
)