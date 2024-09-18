package miscellaneous.navgraph.route

import androidx.compose.runtime.Composable
import miscellaneous.di.OtherInfoComponentProvider
import miscellaneous.ui.route._MessageFromVCDestination

@Composable
fun MessageFromVCDestination(

) {
   _MessageFromVCDestination(
        repository = OtherInfoComponentProvider.getOtherInfoRepository(),
    )
}