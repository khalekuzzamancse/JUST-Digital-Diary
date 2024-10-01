package profile.presentationlogic.factory

import di.DiContainer
import profile.presentationlogic.controller.ProfileController

internal object UiFactory {
    fun createProfileController(token:String):ProfileController=
        ProfileControllerImpl(
            useCase = DiContainer.createGetVCInfoUseCase(token)
        )
}