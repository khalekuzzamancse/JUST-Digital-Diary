@file:Suppress("unused")
package miscellaneous.ui.eventGallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import miscellaneous.presentationlogic.controller.EventGalleryController

class EventGalleryViewmodel internal  constructor(
    internal val controller: EventGalleryController
) :ViewModel(){
    val isLoading = controller.isFetching
    val screenMessage = controller.screenMessage
     init {
       viewModelScope.launch {
             controller.fetch()
         }
     }
}