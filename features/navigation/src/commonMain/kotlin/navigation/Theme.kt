package navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val md_theme_light_primary = Color(0xFFC00214)
val md_theme_light_onPrimary = Color(0xFFFFFFFF)
val md_theme_light_primaryContainer = Color(0xFFFFDAD6)
val md_theme_light_onPrimaryContainer = Color(0xFF410002)
val md_theme_light_secondary = Color(0xFF006E07)
val md_theme_light_onSecondary = Color(0xFFFFFFFF)
val md_theme_light_secondaryContainer = Color(0xFF76FF66)
val md_theme_light_onSecondaryContainer = Color(0xFF002201)
val md_theme_light_tertiary = Color(0xFF3E40F5)
val md_theme_light_onTertiary = Color(0xFFFFFFFF)
val md_theme_light_tertiaryContainer = Color(0xFFE1E0FF)
val md_theme_light_onTertiaryContainer = Color(0xFF05006D)
val md_theme_light_error = Color(0xFFBA1A1A)
val md_theme_light_errorContainer = Color(0xFFFFDAD6)
val md_theme_light_onError = Color(0xFFFFFFFF)
val md_theme_light_onErrorContainer = Color(0xFF410002)
val md_theme_light_background = Color(0xFFFFFBFF)
val md_theme_light_onBackground = Color(0xFF201A19)
val md_theme_light_surface = Color(0xFFFFFBFF)
val md_theme_light_onSurface = Color(0xFF201A19)
val md_theme_light_surfaceVariant = Color(0xFFF5DDDA)
val md_theme_light_onSurfaceVariant = Color(0xFF534341)
val md_theme_light_outline = Color(0xFF857371)
val md_theme_light_inverseOnSurface = Color(0xFFFBEEEC)
val md_theme_light_inverseSurface = Color(0xFF362F2E)
val md_theme_light_inversePrimary = Color(0xFFFFB4AB)
val md_theme_light_shadow = Color(0xFF000000)
val md_theme_light_surfaceTint = Color(0xFFC00214)
val md_theme_light_outlineVariant = Color(0xFFD8C2BF)
val md_theme_light_scrim = Color(0xFF000000)

val md_theme_dark_primary = Color(0xFFFFB4AB)
val md_theme_dark_onPrimary = Color(0xFF690006)
val md_theme_dark_primaryContainer = Color(0xFF93000C)
val md_theme_dark_onPrimaryContainer = Color(0xFFFFDAD6)
val md_theme_dark_secondary = Color(0xFF26E52A)
val md_theme_dark_onSecondary = Color(0xFF003A02)
val md_theme_dark_secondaryContainer = Color(0xFF005304)
val md_theme_dark_onSecondaryContainer = Color(0xFF76FF66)
val md_theme_dark_tertiary = Color(0xFFC0C1FF)
val md_theme_dark_onTertiary = Color(0xFF0C00AA)
val md_theme_dark_tertiaryContainer = Color(0xFF1E17DF)
val md_theme_dark_onTertiaryContainer = Color(0xFFE1E0FF)
val md_theme_dark_error = Color(0xFFFFB4AB)
val md_theme_dark_errorContainer = Color(0xFF93000A)
val md_theme_dark_onError = Color(0xFF690005)
val md_theme_dark_onErrorContainer = Color(0xFFFFDAD6)
val md_theme_dark_background = Color(0xFF201A19)
val md_theme_dark_onBackground = Color(0xFFEDE0DE)
val md_theme_dark_surface = Color(0xFF201A19)
val md_theme_dark_onSurface = Color(0xFFEDE0DE)
val md_theme_dark_surfaceVariant = Color(0xFF534341)
val md_theme_dark_onSurfaceVariant = Color(0xFFD8C2BF)
val md_theme_dark_outline = Color(0xFFA08C8A)
val md_theme_dark_inverseOnSurface = Color(0xFF201A19)
val md_theme_dark_inverseSurface = Color(0xFFEDE0DE)
val md_theme_dark_inversePrimary = Color(0xFFC00214)
val md_theme_dark_shadow = Color(0xFF000000)
val md_theme_dark_surfaceTint = Color(0xFFFFB4AB)
val md_theme_dark_outlineVariant = Color(0xFF534341)
val md_theme_dark_scrim = Color(0xFF000000)


val seed = Color(0xFFE92E2D)


private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary,
    primaryContainer = md_theme_light_primaryContainer,
    onPrimaryContainer = md_theme_light_onPrimaryContainer,
    secondary = md_theme_light_secondary,
    onSecondary = md_theme_light_onSecondary,
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    tertiary = md_theme_light_tertiary,
    onTertiary = md_theme_light_onTertiary,
    tertiaryContainer = md_theme_light_tertiaryContainer,
    onTertiaryContainer = md_theme_light_onTertiaryContainer,
    error = md_theme_light_error,
    errorContainer = md_theme_light_errorContainer,
    onError = md_theme_light_onError,
    onErrorContainer = md_theme_light_onErrorContainer,
    background = md_theme_light_background,
    onBackground = md_theme_light_onBackground,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    surfaceVariant = md_theme_light_surfaceVariant,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
    outline = md_theme_light_outline,
    inverseOnSurface = md_theme_light_inverseOnSurface,
    inverseSurface = md_theme_light_inverseSurface,
    inversePrimary = md_theme_light_inversePrimary,
    surfaceTint = md_theme_light_surfaceTint,
    outlineVariant = md_theme_light_outlineVariant,
    scrim = md_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary,
    primaryContainer = md_theme_dark_primaryContainer,
    onPrimaryContainer = md_theme_dark_onPrimaryContainer,
    secondary = md_theme_dark_secondary,
    onSecondary = md_theme_dark_onSecondary,
    secondaryContainer = md_theme_dark_secondaryContainer,
    onSecondaryContainer = md_theme_dark_onSecondaryContainer,
    tertiary = md_theme_dark_tertiary,
    onTertiary = md_theme_dark_onTertiary,
    tertiaryContainer = md_theme_dark_tertiaryContainer,
    onTertiaryContainer = md_theme_dark_onTertiaryContainer,
    error = md_theme_dark_error,
    errorContainer = md_theme_dark_errorContainer,
    onError = md_theme_dark_onError,
    onErrorContainer = md_theme_dark_onErrorContainer,
    background = md_theme_dark_background,
    onBackground = md_theme_dark_onBackground,
    surface = md_theme_dark_surface,
    onSurface = md_theme_dark_onSurface,
    surfaceVariant = md_theme_dark_surfaceVariant,
    onSurfaceVariant = md_theme_dark_onSurfaceVariant,
    outline = md_theme_dark_outline,
    inverseOnSurface = md_theme_dark_inverseOnSurface,
    inverseSurface = md_theme_dark_inverseSurface,
    inversePrimary = md_theme_dark_inversePrimary,
    surfaceTint = md_theme_dark_surfaceTint,
    outlineVariant = md_theme_dark_outlineVariant,
    scrim = md_theme_dark_scrim,
)
val shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small =  RoundedCornerShape(4.dp),
    medium =  RoundedCornerShape(4.dp),
)

val typography = Typography(
    titleLarge = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),

    )
@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColors
    } else {
        DarkColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}