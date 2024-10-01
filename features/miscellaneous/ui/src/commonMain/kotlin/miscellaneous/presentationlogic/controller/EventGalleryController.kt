@file:Suppress("unused")
package miscellaneous.presentationlogic.controller

import kotlinx.coroutines.flow.StateFlow
import miscellaneous.presentationlogic.model.GalleryEventModel

internal interface EventGalleryController {
    val events: StateFlow<List<GalleryEventModel> >
    val isFetching:StateFlow<Boolean>
    val screenMessage:StateFlow<String?>
    suspend fun fetch()
}