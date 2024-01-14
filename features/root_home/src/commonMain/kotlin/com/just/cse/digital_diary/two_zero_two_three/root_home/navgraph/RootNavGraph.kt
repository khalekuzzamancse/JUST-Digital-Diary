package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition


@Composable
fun RootNavGraph() {
    Navigator(RootNavHost()) { navigator ->
        SlideTransition(navigator = navigator)

    }

}

