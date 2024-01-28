package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar

@Composable
fun TopBarDecorator(
    title: String,
    navigationIcon:ImageVector=Icons.Default.Menu,
    onExitRequest:()->Unit,
    content:@Composable ()->Unit,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                title=title,
                navigationIcon=navigationIcon,
                onNavigationIconClick=onExitRequest
            )
        }
    ) {
        Box(Modifier.padding(it)){
            content()
        }
    }

}