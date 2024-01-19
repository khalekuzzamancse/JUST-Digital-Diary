package com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.two_panes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun CompactModeLayout(
    showPane2:Boolean,
    pane1: @Composable () -> Unit,
    pane2: @Composable () -> Unit,
) {
    Box(Modifier.fillMaxSize()) {
        pane1()
        if (showPane2){
            Box(Modifier.matchParentSize()){
                pane2()
            }
        }
    }
}