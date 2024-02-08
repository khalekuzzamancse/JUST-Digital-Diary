package com.just.cse.digital_diary.two_zero_two_three.root_home

import com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.screens.Destination

data class TopMostDestinationEvent(
    val drawerDestinationNavigationRequest:(Destination)->Unit={},
    val onWebsiteViewRequest:(String)->Unit={}
)
