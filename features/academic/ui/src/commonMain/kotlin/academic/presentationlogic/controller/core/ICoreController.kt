package academic.presentationlogic.controller.core

import common.ui.SnackBarMessage
import kotlinx.coroutines.flow.StateFlow
/**
 * - Almost all the controllers that control the UI has some common state,that state is defined here
 * - The controller that is `abstract` such as `interface` should inherit from it
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