package com.just.cse.digital_diary.two_zero_two_three.root_home.child_destination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digital_diary.two_zero_two_three.event_gallery.EventGalleyModuleEntryPoint

@Composable
internal fun EventGallery(
    onExitRequest:()->Unit
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                onNavigationIconClick = onExitRequest,
                title = "Home",
                navigationIcon = Icons.Default.Menu
            )
        }
    ) {
        Box(Modifier.padding(it)){
            EventGalleyModuleEntryPoint()
        }

    }
}