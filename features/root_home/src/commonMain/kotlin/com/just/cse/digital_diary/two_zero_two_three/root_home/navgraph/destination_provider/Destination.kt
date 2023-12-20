package com.just.cse.digital_diary.two_zero_two_three.root_home.navgraph.destination_provider

interface Destination {
    val route:String
  companion object{
      fun createDestination(route: String) = object : Destination {
          override val route: String = route
      }
  }

}
