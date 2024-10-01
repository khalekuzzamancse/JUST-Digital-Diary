package profile.ui

import profile.presentationlogic.controller.ProfileController

class ProfileViewModel(
    val controller: ProfileController
) {
    val screenMessage = controller.screenMessage
    val isLoading = controller.isFetching
}