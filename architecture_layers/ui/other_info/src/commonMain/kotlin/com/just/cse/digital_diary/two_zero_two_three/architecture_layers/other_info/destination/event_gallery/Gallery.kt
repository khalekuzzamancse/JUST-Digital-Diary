package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.event_gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.event_gallery.EventGalleryResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.OtherInfoRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.event_gallery.state.Event
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.event_gallery.state.EventGalleryDestinationState
import com.just.cse.digital_diary.two_zero_two_three.common_ui.list.AdaptiveList
import com.just.cse.digital_diary.two_zero_two_three.common_ui.progressbar.ProgressBarNSnackBarDecorator


@Composable
fun EventGalleryDestination(
    repository: OtherInfoRepository,
) {
    var state by remember {
        mutableStateOf(
            EventGalleryDestinationState(
                isLoading = true
            )
        )

    }
    LaunchedEffect(Unit) {
        val res = repository.getEvents()
        if (res is EventGalleryResponseModel.Success) {
            state = EventGalleryDestinationState(
                events = res.events.map {
                    Event(
                        name = it.name,
                        details = it.details,
                        imageLink = it.imageLink
                    )
                },
                isLoading = false
            )
        }
    }
    EventGallery(
        state= state,
    )

}

@Composable
private fun EventGallery(
    state: EventGalleryDestinationState,
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