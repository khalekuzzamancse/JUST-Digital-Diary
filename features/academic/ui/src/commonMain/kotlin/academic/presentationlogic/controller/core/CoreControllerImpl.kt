@file:Suppress("propertyName")

package academic.presentationlogic.controller.core

import common.ui.SnackBarMessage
import core.customexception.CustomException
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
    protected val _statusMessage = MutableStateFlow<SnackBarMessage?>(null)

    companion object {
        const val MEG_INSERTION_SUCCESS = ""
    }

    protected fun startLoading() = _isLoading.update { true }
    protected fun stopLoading() = _isLoading.update { false }

    /**Update as neutral messages*/
    protected fun updateStatusMessage(msg: SnackBarMessage) {
        CoroutineScope(Dispatchers.Default).launch {
            _statusMessage.update { msg }
            //clear after 4 seconds
            delay(4_000)
            _statusMessage.update { null }
        }
    }

    protected fun String.updateAsSuccessMessage() =
        updateStatusMessage(SnackBarMessage.success(this))
    protected fun String.updateAsErrorMessage() =
        updateStatusMessage(SnackBarMessage.success(this))


    protected fun Result<Unit>.updateStatusMsg(
        successMsg: String? = null,
        failureMsg: String? = null
    ) {
        this.fold(
            onSuccess = {
                if (successMsg != null) updateStatusMessage(SnackBarMessage.success(successMsg))
            },
            onFailure = { exception ->
                val msg = when (exception) {
                    is CustomException -> exception.message
                    else -> failureMsg ?: "Something went wrong"

                }
                updateStatusMessage(SnackBarMessage.error(msg))
            }
        )

    }

    protected fun Result<Unit>.updateStatusMsg(operationName: String) {
        this.fold(
            onSuccess = {
                updateStatusMessage(SnackBarMessage.success("$operationName success"))
            },
            onFailure = { exception ->
                val msg = when (exception) {
                    is CustomException -> exception.message
                    else -> "$operationName failed"
                }
                updateStatusMessage(SnackBarMessage.error(msg))
            }
        )
    }

    protected fun Throwable.updateStatusMessage(optionalMsg: String? = null) {
        val msg = when (val exception = this) {
            is CustomException -> exception.message
            else -> optionalMsg ?: "Something went wrong"
        }
        updateStatusMessage(SnackBarMessage.error(msg))
    }


}