package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.compact
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.expanded
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.medium
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common.LayoutOnNoExpended

@Composable
fun FacultyLayoutBuilder2Demo () {
    FacultyLayoutBuilder2(
        sectionTypes = listOf("Faculty ","Admin").mapIndexed{ index, it-> SectionType(
            name = it,
            logo = Icons.Outlined.Category,
            sectionId =index
        ) },
        onDeptSelected = {_, _ ->},
        onSectionSelected = {},
        selectedSectionCategoryList = faculties.mapIndexed{ index, it ->
            SectionCategory(
                id = index,
                logo = it.unFocusedIcon,
                name = it.label
            )
        }
    )
}
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun FacultyLayoutBuilder2(
    sectionTypes: List<SectionType>,//
    selectedSectionCategoryList: List<SectionCategory>? = null,//state ,currentSections
    selectedCategoryDeptList: List<Department> ?= null,//
    selectedDeptEmployeeList: List<Employee>?=null,
    onSectionSelected: (Int) -> Unit,//id or index of employee list that should be selected
    onDeptSelected: (sectionIndex: Int, deptIndex: Int) -> Unit,
) {
    val drawerState by remember {
        mutableStateOf(DrawerState(DrawerValue.Open))
    }
    val onDrawerItemClick: (String) -> Unit = {

    }
    val onDrawerGroupClick: (Int) -> Unit = {

    }
    val onDrawerCloseRequested: () -> Unit = {}

    when (calculateWindowSizeClass().widthSizeClass) {
        expanded -> {
//            NavigationRailLayout(
//                selectedDestinationIndex = 0,
//                destinations = destination,
//                onNavRailItemSelected = onNavRailItemSelected,
//            ) {
//                FacultyLayoutOnExpanded()
//            }
        }

        compact, medium -> {
            LayoutOnNoExpended(
                contentSection = {
                    EmployeeList(
                        employees = selectedDeptEmployeeList?: emptyList()
                    )
                },
                tabSection = {
                    // TabSection(
//                        modifier = it,
//                        tabDestination = selectedSectionCategoryList.map { dept->
//                            NavigationItem(
//                                label =dept.fullName,
//                                focusedIcon = dept.logo ?: Icons.Filled.School,
//                                unFocusedIcon = dept.logo ?: Icons.Outlined.School,
//                            )
//                        },
//                        onNavigationIconClick ={
//
//                        }
//                    )
                },
                modifier = Modifier,
                drawerGroups = sectionTypes.map {
                    ModalDrawerGroup(
                        name = it.name,
                        members = (selectedSectionCategoryList ?: emptyList())
                            .map { category ->
                                NavigationItem(
                                    label = category.name,
                                    unFocusedIcon = category.logo,
                                )
                            }
                    )
                },
                drawerState = drawerState,
                onDrawerItemClick = onDrawerItemClick,
                onDrawerCloseRequested = onDrawerCloseRequested,
                snackbarMessage = null,
                onDrawerGroupClick = onDrawerGroupClick
            )
        }

    }


}