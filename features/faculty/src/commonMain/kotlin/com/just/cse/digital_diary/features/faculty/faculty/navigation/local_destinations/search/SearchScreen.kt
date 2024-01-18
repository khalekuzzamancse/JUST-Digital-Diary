package com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.search

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen


internal class SearchScreen(
   private val onExitRequest: () -> Unit,
):Screen{
    @Composable
    override fun Content() {
        FacultyEmployeeSearch(
            onExitRequest = onExitRequest
        )
    }

}
@Composable
private fun FacultyEmployeeSearch(
    onExitRequest: () -> Unit,
) {
//    SearchableEmployeeList(
//        searchMode = true,
//        employeeList = generateDummyEmployeeList(10),
//        onSearchExitRequest = onExitRequest
//    )
}