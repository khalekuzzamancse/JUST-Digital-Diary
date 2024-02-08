package com.just.cse.digital_diary.two_zero_two_three.features.others.functionalities

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.event_gallery.EventGalleryResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.OtherInfoRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.event_gallery.EventGallery
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.event_gallery.state.Event
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.event_gallery.state.EventGalleryState


@Composable
fun EventGalleryDestination(
    repository: OtherInfoRepository,
) {
    var state by remember {
        mutableStateOf(
            EventGalleryState(
                isLoading = true
            )
        )

    }
    LaunchedEffect(Unit) {
        val res = repository.getEvents()
        if (res is EventGalleryResponseModel.Success) {
            state = EventGalleryState(
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
