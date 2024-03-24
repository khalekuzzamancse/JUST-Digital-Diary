package com.just.cse.digitaldiaryremake.justdigitaldiary.twozeotwothree

import administration.navgraph.AdminOfficeFeatureNavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventHandler=AppEventHandler(this)

        setContent {
//            faculty.navgraph.FacultyFeatureNavGraph.NavHost(
//                navController = rememberNavController(),
//                onBackPressed = {},
//                onEvent={}
//            )
            AdminOfficeFeatureNavGraph.NavHost(
                navController = rememberNavController(),
                onBackPress = {},
                onEvent={}
            )
//            AppTheme {
//                AndroidRootNavigation(
//                    onEvent = eventHandler::handleEvent
//                )
//            }
        }
    }
}



