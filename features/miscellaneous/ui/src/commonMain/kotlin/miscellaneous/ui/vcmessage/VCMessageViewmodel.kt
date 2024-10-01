@file:Suppress("unused")
package miscellaneous.ui.vcmessage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import miscellaneous.presentationlogic.controller.VCMessageController

internal class VCMessageViewmodel(
     val controller: VCMessageController
):ViewModel() {
    val isLoading = controller.isFetching
    val screenMessage = controller.screenMessage
    init {
        viewModelScope.launch {
            controller.fetch()
        }
    }
}