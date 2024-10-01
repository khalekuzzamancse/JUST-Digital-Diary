package profile.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import profile.presentationlogic.controller.ProfileController

class ProfileViewModel(
    val controller: ProfileController
):ViewModel() {
    val screenMessage = controller.screenMessage
    val isLoading = controller.isFetching
    init {
        //TODO():Fix it later why causes crash in desktop
//      viewModelScope.launch {
//          controller.fetch()
//      }
        CoroutineScope(Dispatchers.Default).launch {
            controller.fetch()
        }
    }
}