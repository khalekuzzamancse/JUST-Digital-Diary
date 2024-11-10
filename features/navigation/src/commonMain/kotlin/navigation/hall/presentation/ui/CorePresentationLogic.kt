package navigation.hall.presentation.ui

import common.ui.SnackBarMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class CustomException():Throwable(){
    override val message:String="TODO"
    val debugMessage:String="TODO"
}

/**
 * - Almost all the controllers that control the UI has some common state,that state is defined here
 * - The controller that is `abstract` such as `interface` should inherit from it
 * @property isLoading
 * @property statusMessage
 */
interface ICoreController {
    /**
     * Indicates whether a network operation is currently in progress or controller is busy while doing other operations
     *
     * - Uses a name that is independent of any UI framework, ensuring that this layer remains framework-agnostic,that is why
     * should use the name as `showProgressBar` because `progressbar` us UI  dependent.
     * - `isLoading` can be better name because it can represent network operation progress as well as other types of operations,also it is UI independent
     * - `isFetch` , `networkProgressing` though they are valid name but they denote network operation progress, but is possible that for other
     * reason the UI should be in the `loading` state that is why picking the name `isLoading`
     * - Based on this state the  UI can do something such as show Loading state using UI elements or disable something , etc
     */
    val isLoading: StateFlow<Boolean>
    /**
     * A message indicating the status of the operation (success or failure)
     *
     * - `SnackBarMessage` or `ToastMessage` is not a good name because they are UI dependent
     */
    val statusMessage: StateFlow<SnackBarMessage?>
}

/**
 * - The `concrete` controller that is descendant of [ICoreController] can use or inherit it to to reduce the boiler plate code for
 * managing the [ICoreController]
 * - Need not to make an abstract version of it because the child has option to override
 */
internal open class CoreController {
    /**
     * - Don,t remove '_` from the name,because without `_` prefix there may be property that comes from [ICoreController]
     */
    protected val _isLoading = MutableStateFlow(false)
    protected val _statusMessage = MutableStateFlow<SnackBarMessage?>(null)

    protected fun startLoading() = _isLoading.update { true }
    protected fun stopLoading() = _isLoading.update { false }


    protected fun String.showAsSuccessMsg() = _updateMsg(SnackBarMessage.success(this))
    protected fun String.showAsNeutralMsg() = _updateMsg(SnackBarMessage.neutral(this))
    protected fun String.showAsErrorMsg() = _updateMsg(SnackBarMessage.success(this))


    protected fun Result<Unit>.showStatusMsg(successMsg: String? = null, failureMsg: String? = null) {
        this.fold(
            onSuccess = {
                if (successMsg != null) _updateMsg(SnackBarMessage.success(successMsg))
            },
            onFailure = { exception ->
                val msg = when (exception) {
                    is CustomException -> exception.message
                    else -> failureMsg ?: "Something went wrong"
                }
                _updateMsg(SnackBarMessage.error(msg))
            }
        )

    }

    /**
     * @param operation the name of operation such as `Insert` or `Update` `Fetch/Loading` ,`Delete`
     */
    protected fun Result<Unit>.showStatusMsg(operation: String) {
        this.fold(
            onSuccess = {
                _updateMsg(SnackBarMessage.success("$operation success"))
            },
            onFailure = { exception ->
                val msg = when (exception) {
                    is CustomException -> exception.message
                    else -> "$operation failed"
                }
                _updateMsg(SnackBarMessage.error(msg))
            }
        )
    }

    protected fun Throwable.showStatusMsg(optionalMsg: String? = null) {
        val msg = when (val exception = this) {
            is CustomException -> exception.message
            else -> optionalMsg ?: "Something went wrong"
        }
        _updateMsg(SnackBarMessage.error(msg))
    }

    private fun _updateMsg(msg: SnackBarMessage) {
        CoroutineScope(Dispatchers.Default).launch {
            _statusMessage.update { msg }
            //clear after 5 seconds
            delay(5_000)
            _statusMessage.update { null }
        }
    }

}