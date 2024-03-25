package miscellaneous.ui.eventgallery

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ui.list.AdaptiveList
import common.ui.progressbar.ProgressBarNSnackBarDecorator


@Composable
 fun EventGallery(
    state: EventGalleryState,
) {

    ProgressBarNSnackBarDecorator(
        showProgressBar = state.isLoading
    ) {

            AdaptiveList(
                modifier = Modifier,
                items = state.galleryEvents
            ) { event ->
                EventCard(
                    eventName = event.name,
                    eventDetails = event.details,
                    eventImageLink = event.imageLink
                )
            }
        }




}