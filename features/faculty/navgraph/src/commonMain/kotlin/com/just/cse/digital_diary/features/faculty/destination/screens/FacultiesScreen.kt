package com.just.cse.digital_diary.features.faculty.destination.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.faculty.components.functionalities.faculty_list.FacultyAndDepartmentList
import com.just.cse.digitaldiary.twozerotwothree.core.di.faculty.FacultyComponentProvider
/**
 * @param backHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 */
@Composable
 fun FacultiesScreen(
    onTeacherListRequest: (String) -> Unit = {},
    onExitRequest:()->Unit,
    backHandler: @Composable (onBackButtonPress: () -> Unit) -> Unit,
) {
    FacultyAndDepartmentList(
        modifier = Modifier,
        facultyListRepository = FacultyComponentProvider.getFacultyRepository(),
        departmentListRepository = FacultyComponentProvider.getDepartListRepository(),
        onEmployeeListRequest = onTeacherListRequest,
        backHandler=backHandler,
        onExitRequest = onExitRequest
    )


}