package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.event_gallery

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.event_gallery.state.EventGalleryState
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator



@Composable
 fun EventGallery(
    state: EventGalleryState,
) {

    ProgressBarNSnackBarDecorator(
        showProgressBar = state.isLoading
    ) {

            AdaptiveList(
                modifier = Modifier,
                items = state.events
            ) { event ->
                EventCard(
                    eventName = event.name,
                    eventDetails = event.details,
                    eventImageLink = event.imageLink
                )
            }
        }




}