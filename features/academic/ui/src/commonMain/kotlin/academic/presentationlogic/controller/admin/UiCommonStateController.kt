@file:Suppress("propertyName")
package academic.presentationlogic.controller.admin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * - After Implemented  different controller found that  these are common code,that is why extracting them
 * - Need to make an abstract version of it because the child has option to override
 */
internal open class UiCommonStateController {
    /**
     * - Don,t remove '_` from the name,
     */
    protected val _networkIOInProgress = MutableStateFlow(false)
    protected val _statusMessage = MutableStateFlow<String?>(null)

    protected fun onNetworkIOStart() = _networkIOInProgress.update { true }
    protected fun onNetworkIOStop() = _networkIOInProgress.update { false }
    protected fun updateErrorMessage(msg: String) {
        CoroutineScope(Dispatchers.Default).launch {
            _statusMessage.update { msg }
            //clear after 4 seconds
            delay(4_000)
            _statusMessage.update { null }
        }
    }
}