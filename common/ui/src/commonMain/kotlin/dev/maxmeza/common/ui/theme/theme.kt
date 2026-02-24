package dev.maxmeza.common.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AppTheme(isDark: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colorSchema: ColorScheme
    val extraColors: AppThemeExtraColors
    if (isDark) {
        colorSchema = darkScheme
        extraColors = darkExtraColors
    } else {
        colorSchema = lightScheme
        extraColors = lightExtraColors
    }
    val typography = Typography(
        titleMedium = TextStyle(
            fontFamily = poppinsFontFamily(),
            fontWeight = FontWeight.Bold,
            color = AppTheme.extraColors.textPrimary,
            fontSize = 24.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = poppinsFontFamily(),
            fontWeight = FontWeight.Normal,
            color = AppTheme.extraColors.secondaryPrimary,
            fontSize = 16.sp
        ),
        labelMedium = TextStyle(
            fontFamily = poppinsFontFamily(),
            fontWeight = FontWeight.Normal,
            color = AppTheme.extraColors.secondaryPrimary,
            fontSize = 12.sp
        ),
    )
    CompositionLocalProvider(LocalAppThemeExtraColors provides extraColors) {
        CompositionLocalProvider(LocalAppDimensions provides AppDimensions()) {
            MaterialTheme(
                colorScheme = colorSchema,
                typography = typography,
                content = content
            )
        }
    }
}

object AppTheme {
    val dimensions: AppDimensions
        @Composable
        get() = LocalAppDimensions.current
    val extraColors: AppThemeExtraColors
        @Composable
        get() = LocalAppThemeExtraColors.current
}