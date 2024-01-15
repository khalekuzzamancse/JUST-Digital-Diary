package com.just.cse.digital_diary.features.departments.navgraph.local_destinations.search

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.just.cse.digital_diary.features.common_ui.search_bar.SearchableEmployeeList
import com.just.cse.digital_diary.features.common_ui.search_bar.generateDummyEmployeeList

internal class SearchScreen(
   private val onExitRequest: () -> Unit,
): Screen {
    @Composable
    override fun Content() {
        DepartmentEmployeeSearch(
            onExitRequest = onExitRequest
        )
    }

}

@Composable
private fun DepartmentEmployeeSearch(
    onExitRequest: () -> Unit,
) {
    SearchableEmployeeList(
        searchMode = true,
        employeeList = generateDummyEmployeeList(10),
        onSearchExitRequest = onExitRequest
    )
}