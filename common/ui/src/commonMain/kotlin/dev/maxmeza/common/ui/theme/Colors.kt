package dev.maxmeza.common.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val whiteColor = Color(0xFFFFFFFF)
val blackColor = Color(0xFF000000)
val primaryColor = Color(0xFF6446E8)
val primaryLightColor = Color(0xFF8565EC)

val yellowColor = Color(0xFFE89824)
val yellowLightColor = Color(0xFFFCF1C2)

val orangeColor = Color(0xFFFF5800)
val orangeLightColor = Color(0xFFFCD6C2)

val greenColor = Color(0xFF35885F)
val greenLightColor = Color(0xFFD2F7E2)

val blueColor = Color(0xFF2F59E4)
val blueLightColor = Color(0xFFD9E6FD)

val redColor = Color(0xFFFF453E)
val redDarkColor = Color(0xFFAF433A)
val redLightColor = Color(0xFFF9DFDE)

val textSecondaryLightColor = Color(0xFF666666)
val textSecondaryDarkColor = Color(0xFFC2C2C2)


val darkScheme = darkColorScheme(
    primary = primaryColor,
    onPrimary = whiteColor
)

val lightScheme = lightColorScheme(
    primary = primaryColor,
    onPrimary = whiteColor
)

data class AppThemeExtraColors(
    val textPrimary: Color = Color.Unspecified,
    val secondaryPrimary: Color = Color.Unspecified,
    val white: Color = Color.Unspecified,
    val black: Color = Color.Unspecified,
    val indigoLight: Color = Color.Unspecified,
    val green: Color = Color.Unspecified,
    val greenLight: Color = Color.Unspecified,
    val blue: Color = Color.Unspecified,
    val blueLight: Color = Color.Unspecified,
    val red: Color = Color.Unspecified,
    val redDark: Color = Color.Unspecified,
    val redLight: Color = Color.Unspecified,
    val yellow: Color = Color.Unspecified,
    val yellowLight: Color = Color.Unspecified,
    val orange: Color = Color.Unspecified,
    val orangeLight: Color = Color.Unspecified,
    val flashcardBackground: Color = Color.Unspecified,
    val flashcardIconBackground: Color = Color.Unspecified,
    val progressTrack: Color = Color.Unspecified,
    val ratingHardBackground: Color = Color.Unspecified,
    val ratingMediumBackground: Color = Color.Unspecified,
    val ratingEasyBackground: Color = Color.Unspecified,
    val learningTipBackground: Color = Color.Unspecified,
    val learningTipIconBackground: Color = Color.Unspecified,
    val languageBadgeBackground: Color = Color.Unspecified,
    val surfaceCard: Color = Color.Unspecified,
    val canvasBackground: Color = Color.Unspecified,
    val canvasShadowText: Color = Color.Unspecified,
    val canvasStroke: Color = Color.Unspecified,
    val canvasBorder: Color = Color.Unspecified
)

val LocalAppThemeExtraColors = staticCompositionLocalOf { AppThemeExtraColors() }

val lightExtraColors = AppThemeExtraColors(
    textPrimary = blackColor,
    secondaryPrimary = textSecondaryLightColor,
    black = blackColor,
    white = whiteColor,
    indigoLight = primaryLightColor,
    green = greenColor,
    greenLight = greenLightColor,
    blue = blueColor,
    blueLight = blueLightColor,
    red = redColor,
    redDark = redDarkColor,
    redLight = redLightColor,
    yellow = yellowColor,
    yellowLight = yellowLightColor,
    orange = orangeColor,
    orangeLight = orangeLightColor,
    flashcardBackground = Color(0xFFF5F3FF),
    flashcardIconBackground = Color(0xFFE8E4FF),
    progressTrack = Color(0xFFF0F0F0),
    ratingHardBackground = Color(0xFFFCE4EC),
    ratingMediumBackground = Color(0xFFFFF8E1),
    ratingEasyBackground = Color(0xFFE8F5E9),
    learningTipBackground = Color(0xFFF5F3FF),
    learningTipIconBackground = Color(0xFFE8E4FF),
    languageBadgeBackground = Color(0xFFFCE4EC),
    surfaceCard = Color(0xFFF5F3FF),
    canvasBackground = Color(0xFFFFFFFF),
    canvasShadowText = Color(0xFFE0D8F0),
    canvasStroke = primaryColor,
    canvasBorder = Color(0xFFE0D8F0)
)

val darkExtraColors = AppThemeExtraColors(
    textPrimary = whiteColor,
    secondaryPrimary = textSecondaryDarkColor,
    black = blackColor,
    white = whiteColor,
    indigoLight = primaryLightColor,
    green = greenColor,
    greenLight = greenLightColor,
    blue = blueColor,
    blueLight = blueLightColor,
    red = redColor,
    redDark = redDarkColor,
    redLight = redLightColor,
    yellow = yellowColor,
    yellowLight = yellowLightColor,
    orange = orangeColor,
    orangeLight = orangeLightColor,
    flashcardBackground = Color(0xFF1E1B2E),
    flashcardIconBackground = Color(0xFF2A2640),
    progressTrack = Color(0xFF3A3A3A),
    ratingHardBackground = Color(0xFF3A1E22),
    ratingMediumBackground = Color(0xFF3A3020),
    ratingEasyBackground = Color(0xFF1E3024),
    learningTipBackground = Color(0xFF1E1B2E),
    learningTipIconBackground = Color(0xFF2A2640),
    languageBadgeBackground = Color(0xFF3A1E22),
    surfaceCard = Color(0xFF1E1B2E),
    canvasBackground = Color(0xFF252240),
    canvasShadowText = Color(0xFF3A3560),
    canvasStroke = primaryLightColor,
    canvasBorder = Color(0xFF3A3560)
)
