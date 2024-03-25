package miscellaneous.ui.route


import androidx.compose.runtime.Composable
import miscellaneous.ui.home.home.HomeDestination
import miscellaneous.ui.home.home.HomeDestinationEvent

@Composable
fun Home(
    universityLogo: @Composable () -> Unit = {},
    university: @Composable () -> Unit = {},
    onEvent:(OtherFeatureFunctionalityEvent)->Unit
) {
    HomeDestination(
        universityLogo = universityLogo,
        university = university,
        onEvent = {event->
            when(event){
                is HomeDestinationEvent.CalenderViewRequest->onEvent(
                    OtherFeatureFunctionalityEvent.CalenderRequest(event.url)
                )
            }
        }
    )


}