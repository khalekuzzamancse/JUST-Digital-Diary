package common.newui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp


/*
 * Following the single responsibility
 * so it is deal with only two panes,not the snack-bar or progress bar,
 * keeping all related component within this file so that this file can direcly copy-past without
 * any extra dependency.
 * as a result this will only change if two pane related logic need to change.
 *
 */
/**
 * Encapsulate the state or proper so adding  or remove or passing through multiple level
 * can done easily
 */
data class TwoPaneNewUIPros(
    val showTopOrRightPane: Boolean,
    val alignment: Alignment = Alignment.Center,
    val navigationIcon: ImageVector? = null
)

/**
 * Its allow us show the content in pane mode in expanded and the medium screen.
 * in the Compact screen it will show the pane2 top of pane1 as by hiding the pane1
 * * It does not hold the scaffold because scaffold is a compose Layout as  a result making it parent or caller composable
 * as scrollable or how some use sub compose can causes error.
 * there is a another version of that with scaffold
 *
 * @param props ,will ...
 * @param leftPane
 * @param topOrRightPane ,will be the top of pane1 in compact mode and in medium and expanded mode
 * pane2 will be side of pane1 as siblings of Row

 */
@Composable
fun TwoPaneLayout(
    modifier: Modifier,
    props: TwoPaneNewUIPros,
    onNavigationIconClick: (() -> Unit)? = null,
    leftPane: @Composable () -> Unit,
    topOrRightPane: @Composable () -> Unit,
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopBar(props.navigationIcon, onNavigationIconClick)
        }
    ) { scaffoldPadding ->
        _TwoPaneLayout(
            modifier = modifier.padding(scaffoldPadding),
            showTopOrRightPane = props.showTopOrRightPane,
            leftPane = leftPane,
            topOrRightPane = topOrRightPane,
            alignment = props.alignment
        )
    }
}

@Composable
private fun TopBar(
    navigationIcon: ImageVector? = null,
    onNavigationIconClick: (() -> Unit)? = null,
) {
    if (navigationIcon != null) {
        IconButton(onClick = {
            if (onNavigationIconClick != null) {
                onNavigationIconClick()
            }
        }) {
            Icon(
                imageVector = navigationIcon,
                contentDescription = "Navigation Icons"
            )
        }
    }

}

@Composable
private fun _TwoPaneLayout(
    modifier: Modifier,
    showTopOrRightPane: Boolean,
    alignment: Alignment,
    leftPane: @Composable () -> Unit,
    topOrRightPane: @Composable () -> Unit,
) {
    _WindowSizeDecorator(
        onNonCompact = {
            _CompactLayout(
                modifier = modifier,
                showTopPane = showTopOrRightPane,
                leftPane = leftPane,
                topPane = topOrRightPane
            )
        },
        onExpanded = {
            _ExpandedLayout(
                modifier = modifier,
                showTopOrRightPane = showTopOrRightPane,
                leftPane = leftPane,
                topOrRightPane = topOrRightPane,
                alignment = alignment
            )
        }
    )

}

@Composable
private fun _ExpandedLayout(
    modifier: Modifier = Modifier,
    showTopOrRightPane: Boolean,
    alignment: Alignment,
    leftPane: @Composable () -> Unit,
    topOrRightPane: @Composable () -> Unit,
) {
    if (showTopOrRightPane) {
        _SideBySideLayout(
            modifier = modifier,
            leftPane = leftPane,
            topOrRightPane = topOrRightPane,
            alignment = alignment
        )
    } else {
        _SingleLayout(modifier, leftPane)
    }

}

@Composable
private fun _SingleLayout(
    modifier: Modifier = Modifier,
    leftPane: @Composable () -> Unit,
) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        leftPane()
    }
}

@Composable
private fun _SideBySideLayout(
    modifier: Modifier = Modifier,
    alignment: Alignment,
    leftPane: @Composable () -> Unit,
    topOrRightPane: @Composable () -> Unit,
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Box(Modifier.weight(1f), contentAlignment = alignment) {
            leftPane()
        }
        Spacer(Modifier.width(12.dp))
        Box(Modifier.weight(1f), contentAlignment = alignment) {
            topOrRightPane()
        }

    }
}

/**
 * Using private helper function to  avoid coupling with outer files
 * so that direcly can copy paste it
 */
@Composable
private fun _CompactLayout(
    modifier: Modifier = Modifier,
    showTopPane: Boolean,
    enter: EnterTransition = fadeIn() + expandIn(),
    exit: ExitTransition = shrinkOut() + fadeOut(),
    leftPane: @Composable () -> Unit,
    topPane: @Composable () -> Unit,
) {
    if (!showTopPane) {
        leftPane()

    }
    AnimatedVisibility(
        modifier = modifier.fillMaxWidth(),
        enter = enter,
        exit = exit, //TODO: fix the animation transition later
        visible = showTopPane
    ) {
        topPane()
    }

}

/**
 * Using private helper function to  avoid coupling with outer files
 * so that direcly can copy paste it
 */
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun _WindowSizeDecorator(
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
