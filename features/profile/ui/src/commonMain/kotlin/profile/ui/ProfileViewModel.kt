package profile.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import profile.presentationlogic.controller.ProfileController

class ProfileViewModel(
    val controller: ProfileController
):ViewModel() {
    val screenMessage = controller.screenMessage
    val isLoading = controller.isFetching
    init {
        viewModelScope.launch {
            controller.fetch()
        }
    }
}