@file:Suppress("unused")
package miscellaneous.controller_presenter

import miscellaneous.controller_presenter.controller.EventGalleryController
import miscellaneous.controller_presenter.factory.EventGalleryControllerImpl
import miscellaneous.controller_presenter.factory.VCMessageControllerImpl
import miscellaneous.di.DiContainer

internal object UiFactory {
    fun createEventGalleryController(token:String?):EventGalleryController=
        EventGalleryControllerImpl(DiContainer.createGetEventsUseCase(token))
    fun createVCMessageController(token:String?)=VCMessageControllerImpl(
        DiContainer.createGetVCInfoUseCase(token)
    )
}