package miscellaneous.navgraph.route

import androidx.compose.runtime.Composable
import miscellaneous.ui.route.Home
import miscellaneous.ui.route.OtherFeatureFunctionalityEvent

@Composable
fun HomeScreen(
    universityLogo: @Composable () -> Unit = {},
    university: @Composable () -> Unit = {},
    onEvent:(OtherFeatureFunctionalityEvent)->Unit,
) {
    Home(
        universityLogo = universityLogo,
        university = university,
        onEvent = onEvent,
    )


}