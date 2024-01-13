package com.just.cse.digital_diary.features.faculty.faculty

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import com.just.cse.digital_diary.features.common_ui.navigation.bottom_sheet.BottomSheetNavigation
import com.just.cse.digital_diary.features.common_ui.search_bar.SearchSection
import com.just.cse.digital_diary.features.faculty.faculty.navigation.screen.ListOfDepartments
import com.just.cse.digital_diary.features.faculty.faculty.navigation.screen.TopMostDestinationViewModel
import com.just.cse.digital_diary.features.faculty.faculty.navigation.screen.destinations
import com.just.cse.digital_diary.two_zero_two_three.department.department_info.EmployeeCard
import com.just.cse.digital_diary.two_zero_two_three.department.department_info.EmployeeList
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.Employee
import com.just.cse.digitaldiary.twozerotwothree.data.data.repository.generateDummyEmployeeList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T>SearchableEmployeeList(
    destinations: List<NavigationItem<T>>,
    selectedDesertionIndex: Int,
    onDrawerOpenRequested:()->Unit={},
    onDestinationSelected: (Int) -> Unit = {},
) {
    var searchMode by remember { mutableStateOf(false) }
    val topAppbar=@Composable{
        TopAppBar(
            title = {},
            navigationIcon = {
                IconButton(
                    onClick = onDrawerOpenRequested
                ) {
                    Icon(Icons.Default.Menu, null)
                }
            },
            actions = {
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
        visibilityDelay = 70L,
        destinations =destinations ,
        selectedDesertionIndex = selectedDesertionIndex,
        onDestinationSelected = onDestinationSelected,
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


val bottomDestinations = listOf(
    NavigationItem(
        key = 0,
        label = "Employees",
        unFocusedIcon = Icons.Outlined.Person,
        focusedIcon = Icons.Filled.Person
    ),
    NavigationItem(
        key = 1,
        label = "UnderConstruction",
        unFocusedIcon = Icons.Outlined.Work,
        focusedIcon = Icons.Filled.Work
    ),
    NavigationItem(
        key = 2,
        label = "UnderConstruction",
        unFocusedIcon = Icons.Outlined.Work,
        focusedIcon = Icons.Filled.Work
    ),
)

@Composable
fun ScreenUnderConstruction(modifier: Modifier = Modifier) {
    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Under construction")
    }
}