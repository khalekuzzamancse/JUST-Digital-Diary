package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/*
Container color=surface

active indicator color=md.sys.color.secondary-container

active label text color=md.sys.color.on-secondary-container
active icon color=md.sys.color.on-secondary-container


inactive indicator color=md.sys.color.on-surface-variant
inactive icon color=md.sys.color.on-surface-variant


label text text font=md.sys.typescale.label-large.font

onSurface varient =in active indictor+icon color

 */

val md_theme_light_surface = Color(0xFF025172)
val md_theme_light_secondaryContainer = Color(0xFFE8DEF8)
val md_theme_light_onSecondaryContainer = Color.Red
val md_theme_light_onSurface = Color(0xFF1C1B1F)
val md_theme_light_onSurfaceVariant = Color.White
val md_theme_light_neutral_variant20=Color.Blue

private val LightColors = lightColorScheme(
    secondaryContainer = md_theme_light_secondaryContainer,
    onSecondaryContainer = md_theme_light_onSecondaryContainer,
    surface = md_theme_light_surface,
    onSurface = md_theme_light_onSurface,
    onSurfaceVariant = md_theme_light_onSurfaceVariant,
)


private val DarkColors = darkColorScheme(

)

data class Shape(
    val default: RoundedCornerShape = RoundedCornerShape(0.dp),
    val small: RoundedCornerShape = RoundedCornerShape(4.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(8.dp),
    val large: RoundedCornerShape = RoundedCornerShape(16.dp)
)

val LocalShape = compositionLocalOf { Shape() }

val MaterialTheme.shapeScheme: Shape
    @Composable
    @ReadOnlyComposable
    get() = LocalShape.current

@Composable
fun ModalDrawerTheme(
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
        content = content,
    )
}

