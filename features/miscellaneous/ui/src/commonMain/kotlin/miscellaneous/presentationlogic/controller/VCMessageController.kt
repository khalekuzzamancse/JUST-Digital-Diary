@file:Suppress("unused")
package miscellaneous.presentationlogic.controller

import kotlinx.coroutines.flow.StateFlow
import miscellaneous.presentationlogic.model.VCMessageModel

internal interface VCMessageController {
    val events: StateFlow<VCMessageModel? >
    val isFetching:StateFlow<Boolean>
    val screenMessage:StateFlow<String?>
    suspend fun fetch()
}