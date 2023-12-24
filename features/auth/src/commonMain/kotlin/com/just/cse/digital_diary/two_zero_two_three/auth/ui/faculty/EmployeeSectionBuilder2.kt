package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.compact
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.expanded
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.medium
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common.LayoutOnNoExpended
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common.NavGroupSelectedItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.home_screen.HomeScreen
import com.khalekuzzamanjustcse.taskmanagement.ui_layer.theme.AuthModuleTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SectionFactory (
   val  onNavigateToHome:()-> Unit={},
){
    private val _selectedGroupIndexWhoseMembersAreVisible = MutableStateFlow(-1)
    private val _selectedItem = MutableStateFlow(NavGroupSelectedItem())
    val selectedItem = _selectedItem.asStateFlow()

    //
    private val _destinationsOfSelectedSectionChild = MutableStateFlow(emptyList<NavigationItem>())
    val destinationsOfSelectedSectionChild = _destinationsOfSelectedSectionChild.asStateFlow()

    private val _selectedDestinationIndexOfSelectedSectionChild = MutableStateFlow(0)
    val selectedDestinationIndexOfSelectedSectionChild =
        _selectedDestinationIndexOfSelectedSectionChild.asStateFlow()


    private val _navGroups = MutableStateFlow(
        listOf(
            NavigationGroup(
                name = "Home",
                icon = Icons.Outlined.Home,
                members = emptyList()
            ),
            NavigationGroup(
                name = "Faculty",
                icon = Icons.Outlined.School,
                members = emptyList()
            ),
            NavigationGroup(
                name = "Admin Office",
                icon = Icons.Outlined.School,
                members = emptyList()
            )
        )
    )
    val navGroups = _navGroups.asStateFlow()

    fun onGroupSelect(selectedGroupIndex: Int) {
        if(selectedGroupIndex==0){
            onNavigateToHome()
            return
        }
        //

        hideGroupMembersExecptThisIndex(selectedGroupIndex)
        val alreadySelected = _selectedGroupIndexWhoseMembersAreVisible.value == selectedGroupIndex
        if (alreadySelected) {
            //When multiple time selected same group then toggle is member
            if (isMembersAreHidden(selectedGroupIndex))
                showGroupMembers(selectedGroupIndex)
            else
                hideGroupMembers(selectedGroupIndex)
        } else
            showGroupMembers(selectedGroupIndex)
        //
        updateSelectedGroup(selectedGroupIndex)
    }

    private fun isMembersAreHidden(groupIndex: Int): Boolean {
        _navGroups.value.getOrNull(groupIndex)?.let { group ->
            return group.members.isEmpty()
        }
        return false
    }

    private fun hideGroupMembers(groupIndex: Int) {
        _navGroups.update { groups ->
            groups.mapIndexed { index, group ->
                if (groupIndex == index) group.copy(members = emptyList()) else group

            }
        }
    }

    private fun showGroupMembers(selectedGroupIndex: Int) {
        val selectedGroups = _navGroups.value.toMutableList()
        selectedGroups[selectedGroupIndex] = when (selectedGroupIndex) {
            0 -> selectedGroups[selectedGroupIndex].copy(members = faculties)
            1 -> selectedGroups[selectedGroupIndex].copy(members = administrativeOffices)
            else -> selectedGroups[selectedGroupIndex].copy(members = emptyList())
        }
        _navGroups.update { selectedGroups }
    }

    private fun hideGroupMembersExecptThisIndex(selectedGroupIndex: Int) {
        _navGroups.update { groups ->
            groups.mapIndexed { index, group ->
                if (selectedGroupIndex != index) group.copy(members = emptyList()) else group

            }
        }

    }

    private fun updateSelectedGroup(selectedGroupIndex: Int) {
        _selectedGroupIndexWhoseMembersAreVisible.value = selectedGroupIndex
        _selectedDestinationIndexOfSelectedSectionChild.value = 0
    }


    fun onItemSelect(groupIndex: Int, itemIndex: Int) {
        _selectedItem.value = NavGroupSelectedItem(groupIndex, itemIndex)
        when (groupIndex) {

            1 -> {
                when (itemIndex) {
                    0 -> {
                        _destinationsOfSelectedSectionChild.value =
                            toNavigationItem(departmentsOfEngineeringAndTechnology)
                    }

                    1 -> {
                        _destinationsOfSelectedSectionChild.value =
                            toNavigationItem(departmentsOfBiologicalScienceAndTechnology)
                    }

                    2 -> {
                        _destinationsOfSelectedSectionChild.value =
                            toNavigationItem(departmentsOfAppliedScienceAndTechnology)
                    }

                    3 -> {
                        _destinationsOfSelectedSectionChild.value =
                            toNavigationItem(departmentsOfNursingAndHealthScience)
                    }

                    4 -> {
                        _destinationsOfSelectedSectionChild.value =
                            toNavigationItem(departmentOfEnglish)
                    }

                    5 -> {
                        _destinationsOfSelectedSectionChild.value =
                            toNavigationItem(departmentsOfScience)
                    }

                    6 -> {
                        _destinationsOfSelectedSectionChild.value =
                            toNavigationItem(departmentsOfBusinessAndFinance)
                    }
                }
                _selectedDestinationIndexOfSelectedSectionChild.value = 0
            }
        }
    }

    private fun toNavigationItem(list: List<Department2>): List<NavigationItem> {
        return list.map {
            NavigationItem(
                label = it.fullName,
                unFocusedIcon = it.icon
            )
        }
    }


    fun onSelectedDestinationOfSelectedSectionChildChanged(index: Int) {
        _selectedDestinationIndexOfSelectedSectionChild.value = index
    }


}

/*
 */
class MainScreen: Screen {
    @Composable
    override fun Content() {
        val navigator= LocalNavigator.current
        FacultyLayoutBuilder2Demo(
            onNavigateToHome = {
                navigator?.push(HomeScreen())
            }
        )

    }

}
@Composable
fun FacultyLayoutBuilder2Demo(
    onNavigateToHome: () -> Unit={},
) {

    val factory = remember {
        SectionFactory(onNavigateToHome = {
            onNavigateToHome()
        })
    }
    AuthModuleTheme{
        FacultyLayoutBuilder2(
            sections = factory.navGroups.collectAsState().value,
            onSectionSelected = factory::onGroupSelect,
            onSectionChildSelected = factory::onItemSelect,
            sectionSelectionChild = factory.selectedItem.collectAsState().value,
            destinationOfSelectedSectionChild = factory.destinationsOfSelectedSectionChild.collectAsState().value,
            selectedDestinationIndexOfSelectedSectionChild = factory.selectedDestinationIndexOfSelectedSectionChild.collectAsState().value,
            onSelectedDestinationOfSelectedSectionChildChanged = factory::onSelectedDestinationOfSelectedSectionChildChanged
        )
   }



}


/*
It will take the navigation items only

it takes the navigation group,so you have choice:

know which group is clicked
which member is clicked
pass only the group names
pass group name and only the selected group members
pass groups and all members

 */

/*

 */

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun FacultyLayoutBuilder2(
    sections: List<NavigationGroup>,
    onSectionSelected: (Int) -> Unit,
    onSectionChildSelected: (groupIndex: Int, itemIndex: Int) -> Unit,
    sectionSelectionChild: NavGroupSelectedItem,
    destinationOfSelectedSectionChild: List<NavigationItem>,
    selectedDestinationIndexOfSelectedSectionChild: Int,
    onSelectedDestinationOfSelectedSectionChildChanged: (Int) -> Unit
) {
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
                content = { mod ->
                    DepartmentList2(
                        modifier = mod,
                        departments = destinationOfSelectedSectionChild.map {
                            Department2(
                                fullName = it.label,
                                shortName = ""
                            )
                        }
                    )

//                    println("Child Destination:index = $selectedDestinationIndexOfSelectedSectionChild")
//                    TabSection(
//                        modifier = Modifier,
//                        destinations = destinationOfSelectedSectionChild,
//                        selectedItem = selectedDestinationIndexOfSelectedSectionChild,
//                        onItemSelected = onSelectedDestinationOfSelectedSectionChildChanged
//                    )

                },
                topSection = {

                },
                navigationGroups = sections,
                onNavigationGroupClicked = onSectionSelected,
                selectedItem = sectionSelectionChild,
                onNavigationItemClick = onSectionChildSelected
            )
        }

    }


}