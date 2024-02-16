package com.just.cse.digital_diary.two_zero_two_three.features.others.functionalities


import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.HomeDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.components.home.HomeDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.network_image.ImageLoader
import com.just.cse.digital_diary.two_zero_two_three.features.others.event.OtherFeatureFunctionalityEvent

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