package miscellaneous.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import miscellaneous.domain.repoisitory.OtherInfoRepository
import miscellaneous.ui.eventgallery.GalleryEvent
import miscellaneous.ui.eventgallery.EventGallery
import miscellaneous.ui.eventgallery.EventGalleryState


@Composable
internal fun _EventGalleryDestination(
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
        if (res.isSuccess) {
            val data=res.getOrNull()
            if (data!=null){
                state = EventGalleryState(
                    galleryEvents = data.map {
                        GalleryEvent(
                            name = it.name,
                            details = it.details,
                            imageLink = it.imageLink
                        )
                    },
                    isLoading = false
                )
            }

        }
    }
    EventGallery(
        state= state,
    )


}
