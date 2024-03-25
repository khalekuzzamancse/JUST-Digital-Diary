package miscellaneous.ui.eventgallery

data class EventGalleryState(
    val galleryEvents: List<GalleryEvent> = emptyList(),
    val isLoading:Boolean=false
)
data class GalleryEvent(
    val name: String,
    val details:String,
    val imageLink:String
)