package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.event_gallery

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
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
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar


@Composable
fun EventGalleryDestination(
    repository: OtherInfoRepository,
    onExitRequest: () -> Unit,
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
        onExitRequest = onExitRequest
    )

}

@Composable
private fun EventGallery(
    state: EventGalleryDestinationState,
    onExitRequest: () -> Unit
) {

    ProgressBarNSnackBarDecorator(
        showProgressBar = state.isLoading
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


}