package dev.maxmeza.coreDatabase.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeckEntity(
    @PrimaryKey(autoGenerate = true) val deckId: Long = 0,
    val title: String,
    val content: String
)