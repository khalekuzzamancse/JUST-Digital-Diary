@file:Suppress("unused")
package miscellaneous.presentationlogic

import miscellaneous.presentationlogic.controller.EventGalleryController
import miscellaneous.presentationlogic.factory.EventGalleryControllerImpl
import miscellaneous.presentationlogic.factory.VCMessageControllerImpl
import miscellaneous.di.DiContainer

internal object UiFactory {
    fun createEventGalleryController(token:String?):EventGalleryController=
        EventGalleryControllerImpl(DiContainer.createGetEventsUseCase(token))
    fun createVCMessageController(token:String?)=VCMessageControllerImpl(
        DiContainer.createGetVCInfoUseCase(token)
    )
}