package profile.presentationlogic.controller

import common.ui.SnackBarMessage
import kotlinx.coroutines.flow.StateFlow
import profile.presentationlogic.model.ProfileModel

interface ProfileController {
    val profile: StateFlow<ProfileModel>
    val isFetching: StateFlow<Boolean>
    val screenMessage: StateFlow<SnackBarMessage?>
    suspend fun fetch()
}