package com.just.cse.digital_diary.features.faculty.destination.nav_graph

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.just.cse.digital_diary.features.faculty.destination.screens.FacultyListScreen

@Composable
fun FacultyFeatureNavGraph() {
    Navigator(FacultyListScreen())
}