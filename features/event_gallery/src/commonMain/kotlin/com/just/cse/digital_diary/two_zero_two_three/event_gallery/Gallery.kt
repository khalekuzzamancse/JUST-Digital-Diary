package com.just.cse.digital_diary.two_zero_two_three.event_gallery

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar
import com.just.cse.digitaldiary.twozerotwothree.data.repository.event_gallery_repository.repository.EventGalleryRepository

@Composable
internal fun EventGallery(
    onExitRequest:()->Unit
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = "Event Gallery",
                onNavigationIconClick = onExitRequest,
                navigationIcon = Icons.Default.Menu
            )
        }
    ) {
        AdaptiveList(
            modifier = Modifier.padding(it),
            items = EventGalleryRepository.events
        ){
            EventCard(
                eventName =it.name,
                eventDetails = it.details,
                eventImageLink = it.imageLink
            )
        }
    }


}