package dev.maxmeza.common.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.maxmeza.flashlearn.common.ui.resources.Poppins_Bold
import dev.maxmeza.flashlearn.common.ui.resources.Poppins_Medium
import dev.maxmeza.flashlearn.common.ui.resources.Poppins_Regular
import dev.maxmeza.flashlearn.common.ui.resources.Poppins_SemiBold
import dev.maxmeza.flashlearn.common.ui.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun poppinsFontFamily() = FontFamily(
    Font(Res.font.Poppins_Regular, FontWeight.Normal),
    Font(Res.font.Poppins_Medium, FontWeight.Medium),
    Font(Res.font.Poppins_SemiBold, FontWeight.SemiBold),
    Font(Res.font.Poppins_Bold, FontWeight.Bold)
)





@Immutable
data class AppThemeExtraTypography(
    val h1TextStyle: TextStyle = TextStyle.Default,
    val h2TextStyle: TextStyle = TextStyle.Default,
    val h3TextStyle: TextStyle = TextStyle.Default,
    val h4TextStyle: TextStyle = TextStyle.Default,
    val h5TextStyle: TextStyle = TextStyle.Default,
    val bodyTextStyle: TextStyle = TextStyle.Default,
    val captionTextStyle: TextStyle = TextStyle.Default,
)

val appThemeExtraTypography
    @Composable
    get() = AppThemeExtraTypography(
    h1TextStyle = TextStyle(
            fontFamily = poppinsFontFamily(),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 32.sp
        ),
    h2TextStyle = TextStyle(
            fontFamily = poppinsFontFamily(),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 24.sp
    )
)

//val LocalAppThemeExtraTypography = staticCompositionLocalOf { appThemeExtraTypography }
