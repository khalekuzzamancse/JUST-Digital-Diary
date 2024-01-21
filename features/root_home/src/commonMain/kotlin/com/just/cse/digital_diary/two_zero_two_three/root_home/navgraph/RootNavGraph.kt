package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.two_zero_two_three.root_home.AppEvent


@Composable
fun RootNavGraph(event: AppEvent) {
    Navigator(RootNavHost(appEvent=event))

}

