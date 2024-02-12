package com.just.cse.digital_diary.two_zero_two_three.root_home.modal_drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Header(
    modifier: Modifier = Modifier,
    onLogOutRequest: () -> Unit,
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.Blue, Color.Magenta),
        start = Offset(0f, 0f),
        end = Offset(300f, 300f)
    )

    Surface (
        modifier= Modifier.padding(bottom = 4.dp),
        shadowElevation = 8.dp
    ){
        Box(modifier = modifier.background(brush = gradientBrush)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                IconButton(
                    onClick = onLogOutRequest
                ){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "logout button"
                    )
                }
            }
        }
    }
}
