@file:Suppress("unused")
package miscellaneous.ui.eventGallery

import miscellaneous.presentationlogic.controller.EventGalleryController

 class EventGalleryViewmodel internal  constructor(
    internal val controller: EventGalleryController
) {
    val isLoading = controller.isFetching
    val screenMessage = controller.screenMessage
}