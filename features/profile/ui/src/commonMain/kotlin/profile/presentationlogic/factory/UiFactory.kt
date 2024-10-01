package profile.presentationlogic.factory

import di.DiContainer
import profile.presentationlogic.controller.ProfileController

internal object UiFactory {
    fun createProfileContoller(token:String):ProfileController=
        ProfileControllerImpl(
            useCase = DiContainer.createGetVCInfoUseCase(token)
        )
}