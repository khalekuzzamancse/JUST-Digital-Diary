package com.just.cse.digital_diary.two_zero_two_three.common_ui.layout

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.CompactModeLayout
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.NonCompactModeLayout
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes.TwoPaneProps


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
    snackBarMessage: String? = null,
    showProgressBar: Boolean = false,
    props:TwoPaneProps=TwoPaneProps(),
    showTopOrRightPane: Boolean,
    secondaryPaneAnimationState:Any?,
    leftPane: @Composable () -> Unit,
    topOrRightPane: @Composable () -> Unit,
) {
    WindowSizeDecorator(
        snackBarMessage = snackBarMessage,
        showProgressBar = showProgressBar,
        onCompact = {
            CompactModeLayout(
                showTopPane = showTopOrRightPane,
                enter = props.topPaneAnimation.enter,
                exit = props.topPaneAnimation.exit,
                pane1 = leftPane,
                topPane = {
                        topOrRightPane()
                        println("Visible")
                }
            )

        },
        onNonCompact = {
            NonCompactModeLayout(
                showPane2 = showTopOrRightPane,
                pane1MaxWithPortion =props.pane1MaxWidthPortion,
                pane1 = leftPane,
                pane2 = topOrRightPane,
                horizontalSpacing = props.horizontalSpace,
                pane1FillMaxWidth = props.pane1FillMaxWidth,
                pane2AnimationState=secondaryPaneAnimationState,
            )
        }
    )
}
