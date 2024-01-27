package com.just.cse.digital_diary.two_zero_two_three.root_home.destinations.destinations

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.home.HomeDestination
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.home.HomeDestinationEvent

@Composable
fun HomeScreen(
    onEvent:(HomeDestinationEvent)->Unit,
) {
    HomeDestination(onEvent =onEvent )

}