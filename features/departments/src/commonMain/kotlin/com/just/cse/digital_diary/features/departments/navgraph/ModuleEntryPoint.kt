package com.just.cse.digital_diary.features.departments.navgraph

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.departments.navgraph.local_destinations.dept_list.DepartmentList
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.FacultyRepository

/*
this only the entry point to this module from the outside module
 */
@Composable
fun DepartmentListModuleEntryPoint(
    facultyId: String,
    onExitRequest: () -> Unit
) {
    NavGraph(
        facultyId =facultyId,
        onExitRequest = onExitRequest
    )

}