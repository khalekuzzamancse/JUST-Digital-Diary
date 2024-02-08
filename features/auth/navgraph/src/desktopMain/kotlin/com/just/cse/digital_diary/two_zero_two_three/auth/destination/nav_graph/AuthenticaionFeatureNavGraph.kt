package com.just.cse.digital_diary.two_zero_two_three.auth.destination.nav_graph

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.two_zero_two_three.auth.destination.screens.AuthenticationScreen

@Composable
fun AuthenticationFeatureNavGraph() {
    Navigator(AuthenticationScreen())

}