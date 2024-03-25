package common.ui.layout

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import common.ui.WindowSizeDecorator
import common.ui.layout.two_panes.CompactModeLayout
import common.ui.layout.two_panes.TwoPaneProps

/**
 * Its allow us show the content in pane mode in expanded and the medium screen.
 * in the Compact screen it will show the pane2 top of pane1 as by hiding the pane1
 * Its thumb disappears when the scrolling container is dormant.
 * @param snackBarMessage(optional) a [String] for show show the snack-bar message
 * @param showProgressBar (optional) a [Boolean] to show the progress bar.
 * @param showTopOrRightPane ,will used to hide or show the pane2
 * @param leftPane
 * @param topOrRightPane ,will be the top of pane1 in compact mode and in medium and expanded mode
 * pane2 will be side of pane1 as siblings of Row
 */
@Composable
fun TwoPaneLayout(
    modifier: Modifier,
    snackBarMessage: String? = null,
    showProgressBar: Boolean = false,
    props: TwoPaneProps = TwoPaneProps(),
    showTopOrRightPane: Boolean,
    alignment: Alignment = Alignment.Center,
    leftPane: @Composable () -> Unit,
    navigationIcon: ImageVector? = null,
    onNavigationIconClick:( () -> Unit)? =null,
    topOrRightPane: @Composable () -> Unit,
) {
    Scaffold(
        modifier = Modifier,
        topBar = {
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
    ) { scaffoldPadding ->
    TwoPaneLayout(
        modifier = modifier.padding(scaffoldPadding),
        snackBarMessage = snackBarMessage,
        showProgressBar = showProgressBar,
        props = props,
        showTopOrRightPane = showTopOrRightPane,
        alignment = alignment,
        leftPane = leftPane,
        topOrRightPane = topOrRightPane
    )
     }
}

@Composable
fun TwoPaneLayout(
    modifier: Modifier,
    snackBarMessage: String? = null,
    showProgressBar: Boolean = false,
    props: TwoPaneProps = TwoPaneProps(),
    showTopOrRightPane: Boolean,
    alignment: Alignment = Alignment.Center,
    leftPane: @Composable () -> Unit,
    topOrRightPane: @Composable () -> Unit,
) {
    WindowSizeDecorator(
        modifier = modifier,
        snackBarMessage = snackBarMessage,
        showProgressBar = showProgressBar,
        onNonExpanded = {
            CompactModeLayout(
                showTopPane = showTopOrRightPane,
                enter = props.topPaneAnimation.enter,
                exit = props.topPaneAnimation.exit,
                pane1 = leftPane,
                topPane = topOrRightPane
            )

        },
        onExpanded = {
            Row(modifier = Modifier.fillMaxWidth()) {
                if (showTopOrRightPane) {
                    Box(Modifier.weight(1f)) {
                        leftPane()
                    }
                    Spacer(Modifier.width(props.horizontalSpace))
                    Box(Modifier.weight(1f)) {
                        topOrRightPane()
                    }
                } else {
                    Box(Modifier.fillMaxSize(), contentAlignment = alignment) {
                        leftPane()
                    }

                }
            }
        }
    )
}
