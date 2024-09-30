@file:Suppress("unused")
package miscellaneous.controller_presenter.controller

import kotlinx.coroutines.flow.StateFlow
import miscellaneous.controller_presenter.model.VCMessageModel

internal interface VCMessageController {
    val events: StateFlow<VCMessageModel? >
    val isFetching:StateFlow<Boolean>
    val screenMessage:StateFlow<String?>
    suspend fun fetch()
}