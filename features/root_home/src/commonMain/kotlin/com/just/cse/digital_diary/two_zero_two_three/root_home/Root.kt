package com.just.cse.digital_diary.two_zero_two_three.root_home

import androidx.compose.runtime.Composable
import com.just.cse.digital_diary.features.faculty.faculty.destination.FacultiesScreen
import com.just.cse.digital_diary.features.faculty.faculty.destination.FacultyListDestination
import com.just.cse.digital_diary.two_zero_two_three.auth.AuthScreen
import com.just.cse.digital_diary.two_zero_two_three.root_home.ui.themes.AppTheme


@Composable
fun RootModule(appEvent: AppEvent) {
    AppTheme {
       // AuthScreen()
        FacultiesScreen(onDepartmentSelected = {
            println("Hoome:SelectedDepartment$it")
        })

  }


}



