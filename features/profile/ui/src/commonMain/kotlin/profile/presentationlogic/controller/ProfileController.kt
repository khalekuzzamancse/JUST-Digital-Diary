package profile.presentationlogic.controller

import kotlinx.coroutines.flow.StateFlow
import profile.presentationlogic.model.ProfileModel

interface ProfileController {
    val profile: StateFlow<ProfileModel>
    val isFetching: StateFlow<Boolean>
    val screenMessage: StateFlow<String?>
    suspend fun fetch()
}