@file:Suppress("propertyName")

package academic.presentationlogic.controller.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * - The `concrete` controller that is descendant of [CoreController] can use or inherit it to to reduce the boiler plate code for
 * managing the [CoreController]
 * - Need not to make an abstract version of it because the child has option to override
 */
internal open class CoreControllerImpl {
    /**
     * - Don,t remove '_` from the name,because without `_` prefix there may be property that comes from [CoreController]
     */
    protected val _isLoading = MutableStateFlow(false)
    protected val _statusMessage = MutableStateFlow<String?>(null)

    protected fun startLoading() = _isLoading.update { true }
    protected fun stopLoading() = _isLoading.update { false }
    protected fun updateErrorMessage(msg: String) {
        CoroutineScope(Dispatchers.Default).launch {
            _statusMessage.update { msg }
            //clear after 4 seconds
            delay(4_000)
            _statusMessage.update { null }
        }
    }
}