package com.just.cse.digital_diary.features.common_ui.progressbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ProgressBarDecorator(
    modifier: Modifier = Modifier,
    showProgressBar: Boolean,
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier
    ) {
        content()
        AnimatedVisibility(
            visible = showProgressBar
        ){
            Box(Modifier.fillMaxSize().background(Color.Gray.copy(alpha = 0.7f))){
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }

    }

}