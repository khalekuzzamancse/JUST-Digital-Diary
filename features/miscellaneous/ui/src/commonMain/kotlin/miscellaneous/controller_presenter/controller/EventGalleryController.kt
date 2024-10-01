@file:Suppress("unused")
package miscellaneous.controller_presenter.controller

import kotlinx.coroutines.flow.StateFlow
import miscellaneous.controller_presenter.model.GalleryEventModel

internal interface EventGalleryController {
    val events: StateFlow<List<GalleryEventModel> >
    val isFetching:StateFlow<Boolean>
    val screenMessage:StateFlow<String?>
    suspend fun fetch()
}