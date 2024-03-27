package common.newui

import androidx.compose.animation.AnimatedContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
 fun WindowSizeDecoratorNew(
    onNonCompact: @Composable () -> Unit,
    onExpanded: @Composable () -> Unit,
) {
    val windowSize = calculateWindowSizeClass().widthSizeClass
    val compact = WindowWidthSizeClass.Compact
    val medium = WindowWidthSizeClass.Medium
    val expanded = WindowWidthSizeClass.Expanded

    AnimatedContent(windowSize) { window ->
        when (window) {
            compact, medium -> onNonCompact()
            expanded -> onExpanded()
        }
    }
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun WindowSizeDecoratorNew(
    onMedium:@Composable ()->Unit,
    onCompact: @Composable () -> Unit,
    onExpanded: @Composable () -> Unit,
) {
    val windowSize = calculateWindowSizeClass().widthSizeClass
    val compact = WindowWidthSizeClass.Compact
    val medium = WindowWidthSizeClass.Medium
    val expanded = WindowWidthSizeClass.Expanded

    AnimatedContent(windowSize) { window ->
        when (window) {
            compact -> onCompact()
            medium -> onMedium()
            expanded -> onExpanded()
        }
    }
}
