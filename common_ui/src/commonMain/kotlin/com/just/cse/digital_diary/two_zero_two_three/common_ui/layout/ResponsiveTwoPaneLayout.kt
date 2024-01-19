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
 * @param showPane2 ,will used to hide or show the pane2
 * @param pane1
 * @param pane2 ,will be the top of pane1 in compact mode and in medium and expanded mode
 * pane2 will be side of pane1 as siblings of Row
 */
@Composable
fun TwoPaneLayout(
    snackBarMessage: String? = null,
    showProgressBar: Boolean = false,
    props:TwoPaneProps=TwoPaneProps(),
    showPane2: Boolean,
    pane2AnimationState:Any?=false,
    pane1: @Composable () -> Unit,
    pane2: @Composable () -> Unit,
) {
    WindowSizeDecorator(
        snackBarMessage = snackBarMessage,
        showProgressBar = showProgressBar,
        onCompact = {
            CompactModeLayout(
                showPane2 = showPane2,
                pane1 = pane1,
                pane2 = pane2
            )

        },
        onNonCompact = {
            NonCompactModeLayout(
                showPane2 = showPane2,
                pane1MaxWithPortion =props.pane1MaxWidthPortion,
                pane1 = pane1,
                pane2 = pane2,
                horizontalSpacing = props.horizontalSpace,
                pane1FillMaxWidth = props.pane1FillMaxWidth,
                pane2AnimationState=pane2AnimationState,
            )
        }
    )
}
