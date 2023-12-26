package com.just.cse.digital_diary.two_zero_two_three.auth.navigation_demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow

/*
One of the solution is to give a navigator to the screen,or get the local navigator
it will manage it,
the other solution is to is to completely give a separate navigator,


 */
@Composable
fun DemoNav() {
    Navigator(screen = DemoNavHost())
}

class DemoNavHost : Screen {
    private var navigator: Navigator? = null

    @Composable
    override fun Content() {
        navigator = LocalNavigator.current
        Navigator(ScreenA())
        println("NaHost:Parent:${navigator?.parent},navigator:${navigator}")
    }


}

class ScreenA : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Column {
            Text("This is A")
            Button(
                onClick = {
                    println("A :Parent:${navigator?.parent},navigator:${navigator}")
                    navigator?.push(ModuleB())
                }
            ) {
                Text("Go to B")
            }
        }

    }

    class ModuleB : Screen {
        @Composable
        override fun Content() {
            Navigator(ScreenB())
        }

    }

    class ScreenB : Screen {

        @Composable
        override fun Content() {
            val navigator = LocalNavigator.current
            Column {
                Text("This is B")
                Button(onClick = {
                    if (navigator != null) {
                        println("B :Parent:${navigator.parent},navigator:${navigator}")
                    }
                    navigator?.push(ScreenC())
                }) {
                    Text("Go to C")
                }
            }

        }
    }


    class ScreenC : Screen {

        @Composable
        override fun Content() {
            val navigator = LocalNavigator.current
            Column {
                Text("This is C")
                Button(
                    onClick = {
                        if (navigator != null) {
                            println("C :Parent:${navigator.parent},navigator:${navigator}")
                            navigator.push(ScreenD())
                        }
                    }
                ) {
                    Text("Go to D")
                }
            }

        }

    }

    class ScreenD : Screen {

        @Composable
        override fun Content() {
            val navigator = LocalNavigator.current
            if (navigator != null) {
                println("D :Parent:${navigator.parent},navigator:${navigator}")
            }
            Column {
                Text("This is D")

            }

        }

    }
}

