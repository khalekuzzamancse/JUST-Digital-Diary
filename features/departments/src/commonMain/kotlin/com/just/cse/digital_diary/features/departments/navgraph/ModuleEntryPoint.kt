package com.just.cse.digital_diary.features.departments.navgraph

import androidx.compose.runtime.Composable

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