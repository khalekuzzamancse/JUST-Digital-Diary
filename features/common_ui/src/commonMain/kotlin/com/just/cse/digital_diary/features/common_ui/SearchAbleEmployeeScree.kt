package com.just.cse.digital_diary.features.common_ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.features.common_ui.navigation.bottom_sheet.BottomSheetNavigation
import com.just.cse.digital_diary.features.common_ui.search_bar.SearchSection
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.Employee
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T>SearchableEmployeeList(
    destinations: List<NavigationItem<T>>,
    selectedDesertionIndex: Int,
    navigationIcon:ImageVector =Icons.Default.ArrowBack,
    onDrawerOpenRequested:()->Unit,
    onDestinationSelected: (Int) -> Unit,
) {
    var searchMode by remember { mutableStateOf(false) }
    val sheetState= rememberModalBottomSheetState()
    val scope= rememberCoroutineScope()

    val topAppbar=@Composable{
        TopAppBar(
            title = {
                    Text("Employee")
            },
            navigationIcon = {
                IconButton(
                    onClick = onDrawerOpenRequested
                ) {
                    Icon(navigationIcon, null)
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        scope.launch {
                            sheetState.expand()
                        }
                    }
                ) {
                    Icon(Icons.Default.OpenInNew, null)
                }
                IconButton(
                    onClick = {
                        searchMode = true
                    }
                ) {
                    Icon(Icons.Default.Search, null)
                }

            }
        )
    }

    BottomSheetNavigation(
        visibilityDelay =0L,
        destinations =destinations ,
        selectedDesertionIndex = selectedDesertionIndex,
        onDestinationSelected = onDestinationSelected,
        sheetState = sheetState,
        topBar = if(!searchMode)topAppbar else null
    ) {
        SearchableEmployeeList(
            searchMode = searchMode,
            employeeList = generateDummyEmployeeList(10),
            onSearchExitRequest = {
                searchMode = false
            }
        )
    }


}
@Composable
fun SearchableEmployeeList(
    searchMode: Boolean = false,
    onSearchExitRequest:()->Unit,
    employeeList: List<Employee>,
) {

    AnimatedVisibility(
        visible = searchMode
    ) {
        SearchSection(
            onSearchExitRequest =onSearchExitRequest,
            predicate = { employee, queryText ->
                predicate(employee, queryText)
            },
            items = employeeList,
            searchedItemDecorator = { employee, queryText ->
                EmployeeCard(
                    modifier = Modifier,
                    highlightedText = queryText,
                    employee = employee
                )
            },

        )
    }
    AnimatedVisibility(
        visible = !searchMode
    ){
        EmployeeList(
            modifier = Modifier,
            employees = employeeList,
        )
    }
}



fun predicate(employee: Employee, queryText: String): Boolean {
    return (
            employee.name.contains(queryText, ignoreCase = true)
                    || employee.deptName.contains(queryText, ignoreCase = true)
                    || employee.deptSortName.contains(queryText, ignoreCase = true)
                    || employee.email.contains(queryText, ignoreCase = true)
            )
}
