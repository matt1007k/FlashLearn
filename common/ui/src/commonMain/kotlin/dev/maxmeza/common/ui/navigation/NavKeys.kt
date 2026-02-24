package dev.maxmeza.common.ui.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route: NavKey {
    @Serializable
    object Home: NavKey

    @Serializable
    data class Study(val deckId: String): NavKey

    @Serializable
    object Profile: NavKey

    @Serializable
    object Add: NavKey
}