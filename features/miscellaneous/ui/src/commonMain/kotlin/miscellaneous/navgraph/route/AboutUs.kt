package miscellaneous.navgraph.route

import androidx.compose.runtime.Composable
import miscellaneous.di.OtherInfoComponentProvider
import miscellaneous.ui.route._AboutUsDestination


@Composable
fun AboutUsDestination(
) {
    _AboutUsDestination(
        repository = OtherInfoComponentProvider.getOtherInfoRepository(),
    )

}