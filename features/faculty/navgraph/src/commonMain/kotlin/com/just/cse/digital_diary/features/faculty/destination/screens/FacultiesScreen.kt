package com.just.cse.digital_diary.features.faculty.destination.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.components.functionalities.faculty_list.FacultiesInfoDestination
import com.just.cse.digitaldiary.twozerotwothree.core.di.faculty.FacultyComponentProvider

@Composable
 fun FacultiesScreen(
    onTeacherListRequest: (String) -> Unit = {},
) {
    FacultiesInfoDestination(
        modifier = Modifier,
        facultyListRepository = FacultyComponentProvider.getFacultyRepository(),
        departmentListRepository = FacultyComponentProvider.getDepartListRepository(),
        onEmployeeListRequest = onTeacherListRequest
    )


}