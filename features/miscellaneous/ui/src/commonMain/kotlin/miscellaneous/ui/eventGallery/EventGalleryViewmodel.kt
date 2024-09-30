@file:Suppress("unused")
package miscellaneous.ui.eventGallery

import miscellaneous.controller_presenter.controller.EventGalleryController

 class EventGalleryViewmodel internal  constructor(
    internal val controller: EventGalleryController
) {
    val isLoading = controller.isFetching
    val screenMessage = controller.screenMessage
}