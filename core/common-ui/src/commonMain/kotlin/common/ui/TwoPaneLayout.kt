package common.ui
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * - Can be used for both academic(faculty+dept list) and administrative(office+sub office list)
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
 fun TwoPaneLayout(
    modifier: Modifier = Modifier,
    showSecondPane: Boolean,
    leftPane: @Composable () -> Unit,
    rightOrTopPane: @Composable () -> Unit
) {
    val windowSize = calculateWindowSizeClass().widthSizeClass
    val compact = WindowWidthSizeClass.Compact
    val medium = WindowWidthSizeClass.Medium
    val expanded = WindowWidthSizeClass.Expanded

    AnimatedContent(
        targetState = showSecondPane,
        transitionSpec = {
            if (targetState) {
                (slideInHorizontally { fullWidth -> fullWidth } + fadeIn()).togetherWith(
                    slideOutHorizontally { fullWidth -> -fullWidth } + fadeOut())
            } else {
                (slideInHorizontally { fullWidth -> -fullWidth } + fadeIn()).togetherWith(
                    slideOutHorizontally { fullWidth -> fullWidth } + fadeOut())
            }.using(
                SizeTransform(clip = false)
            )
        }
    ) { showDept ->
        when (windowSize) {
            compact, medium -> {
                if (showDept) {
                    rightOrTopPane()
                } else {
                    leftPane()
                }
            }
            expanded -> {
                Row(modifier = modifier.fillMaxWidth()) {
                    Box(Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                        leftPane()
                    }
                    if (showDept) {
                        Spacer(Modifier.width(12.dp))
                        Box(Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                            rightOrTopPane()
                        }
                    }
                }
            }
        }
    }
}
