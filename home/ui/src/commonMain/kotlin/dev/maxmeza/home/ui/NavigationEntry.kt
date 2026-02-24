package dev.maxmeza.home.ui

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.maxmeza.common.ui.navigation.Navigator
import dev.maxmeza.common.ui.navigation.Route
import dev.maxmeza.home.ui.home.HomeScreen


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun EntryProviderScope<NavKey>.HomeEntry(
    navigator: Navigator
//    backStack: SnapshotStateList<NavKey>
) {
    entry<Route.Home>(
        metadata = ListDetailSceneStrategy.listPane()
    ) {
        HomeScreen(onGoStudy = { deckId ->
            navigator.navigate(Route.Study(deckId))
        })
    }
}