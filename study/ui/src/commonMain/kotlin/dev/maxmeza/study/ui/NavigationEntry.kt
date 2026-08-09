package dev.maxmeza.study.ui

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import dev.maxmeza.common.ui.navigation.Navigator
import dev.maxmeza.common.ui.navigation.Route
import dev.maxmeza.study.ui.study.StudyScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun EntryProviderScope<NavKey>.StudyEntry(
    navigator: Navigator
) {
    entry<Route.Study>(
        metadata = ListDetailSceneStrategy.detailPane()
    ) { entry ->
        StudyScreen(entry.deckId, onBack = navigator::goBack)
    }
}
