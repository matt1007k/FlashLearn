package dev.maxmeza.common.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import dev.maxmeza.common.ui.resources.Drawable
import dev.maxmeza.common.ui.resources.Strings
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class BottomNavItem(
    val icon: DrawableResource,
    val title: StringResource
)

val TOP_LEVEL_DESTINATIONS = mapOf(
    Route.Home to BottomNavItem(
        icon = Drawable.home,
        title = Strings.navHome
    ),
    Route.Add to BottomNavItem(
        icon = Drawable.add,
        title = Strings.navAdd
    ),
    Route.Profile to BottomNavItem(
        icon = Drawable.profile ,
        title = Strings.navProfile
    )
)