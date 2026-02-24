package dev.maxmeza.common.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions(
    val extraSmallMargin: Dp = 5.dp,
    val smallMargin: Dp = 8.dp,
    val defaultPadding: Dp = 16.dp,
    val mediumMargin: Dp = 20.dp,
    val largeMargin: Dp = 24.dp,
    val extraMargin: Dp = 30.dp,
    val sizeNormal: Dp = 32.dp,
    val sizeMedium: Dp = 36.dp,
    val sizeLarge: Dp = 42.dp,
)

val LocalAppDimensions = compositionLocalOf { AppDimensions() }