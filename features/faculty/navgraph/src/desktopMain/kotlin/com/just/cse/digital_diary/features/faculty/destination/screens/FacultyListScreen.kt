package com.just.cse.digital_diary.features.faculty.destination.screens

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.faculty.components.functionalities.faculty_list.FacultiesInfoDestination
import com.just.cse.digitaldiary.twozerotwothree.core.di.faculty.FacultyComponentProvider

internal class FacultyListScreen(
    private val onExitRequest: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        FacultiesInfoDestination(
            facultyListRepository = FacultyComponentProvider.getFacultyRepository(),
            departmentListRepository = FacultyComponentProvider.getDepartListRepository(),
            onEmployeeListRequest =  { deptId ->
                navigator?.push(
                    TeacherListScreen(
                        deptId = deptId,
                        exitRequest = {
                            navigator.pop()
                        }
                    )
                )
            }
        )

    }
}