package com.just.cse.digital_diary.features.faculty.faculty.navigation

import androidx.compose.runtime.Composable

/**
 * * This is Only the entry point to the Faculty module
 * * it will delegate to the NavGraph([NavGraph]) of this module.
 * @param onExitRequested :to exit from the Module
 *
 */
@Composable
fun FacultyModuleEntryPoint(
    onExitRequested: () -> Unit,
) {
    NavGraph(
        onExitRequested = onExitRequested
    )




}

