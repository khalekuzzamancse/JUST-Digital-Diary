package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.event_gallery.EventGalleryDestination
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory.OtherInfoRepositoryImpl


@Composable
internal fun EventGallery(
    onExitRequest: () -> Unit
) {
    Box(Modifier) {
        EventGalleryDestination (
            repository = OtherInfoRepositoryImpl(),
            onExitRequest = onExitRequest
        )
    }


}