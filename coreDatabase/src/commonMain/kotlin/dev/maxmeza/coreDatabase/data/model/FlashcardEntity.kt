package dev.maxmeza.coreDatabase.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class FlashcardEntity(
    @PrimaryKey(autoGenerate = true) val flashcardId: Long = 0,
    val frontSide: String,
    val backSide: String,
    val tags: List<String>,
    val deckOwnerId: Long
)


data class DeckAndFlashcard(
    @Embedded val deck: DeckEntity,
    @Relation(
        parentColumn = "deckId",
        entityColumn = "deckOwnerId"
    )
    val flashcard: FlashcardEntity
)