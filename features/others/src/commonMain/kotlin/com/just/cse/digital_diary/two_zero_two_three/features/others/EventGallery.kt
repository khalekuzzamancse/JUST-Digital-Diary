package com.just.cse.digital_diary.two_zero_two_three.features.others

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.event_gallery.EventGalleryDestination
import com.just.cse.digital_diary.two_zero_two_three.data_layer.admin_officers.repoisitory.OtherInfoRepositoryImpl


@Composable
fun EventGalleryDestination() {
    EventGalleryDestination(
        repository = OtherInfoRepositoryImpl()
    )

}
