package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.event_gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.event_gallery.EventGalleryResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.OtherInfoRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.event_gallery.state.Event
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