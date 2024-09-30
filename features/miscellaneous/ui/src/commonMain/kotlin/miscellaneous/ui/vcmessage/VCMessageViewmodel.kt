@file:Suppress("unused")
package miscellaneous.ui.vcmessage

import miscellaneous.controller_presenter.controller.VCMessageController

internal class VCMessageViewmodel(
     val controller: VCMessageController
) {
    val isLoading = controller.isFetching
    val screenMessage = controller.screenMessage
}