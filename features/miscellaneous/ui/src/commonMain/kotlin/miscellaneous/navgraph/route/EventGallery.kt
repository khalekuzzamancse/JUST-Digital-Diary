package miscellaneous.navgraph.route

import androidx.compose.runtime.Composable
import miscellaneous.di.OtherInfoComponentProvider
import miscellaneous.ui.route._EventGalleryDestination


@Composable
fun EventGalleryDestination(
) {
    _EventGalleryDestination(
        repository = OtherInfoComponentProvider.getOtherInfoRepository()
    )

}
