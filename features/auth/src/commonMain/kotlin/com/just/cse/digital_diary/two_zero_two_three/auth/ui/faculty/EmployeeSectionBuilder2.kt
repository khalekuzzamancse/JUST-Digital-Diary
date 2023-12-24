package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.compact
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.expanded
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.medium
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common.LayoutOnNoExpended3
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common.MyDrawerState
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common.NavGroupSelectedItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SectionFactory {
    private val _selectedGroupIndexWhoseMembersAreVisible = MutableStateFlow(-1)
    private val _selectedItem = MutableStateFlow(NavGroupSelectedItem())
    val selectedItem = _selectedItem.asStateFlow()

    private val _navGroups = MutableStateFlow(
        listOf(
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
    }

    fun onItemSelect(groupIndex: Int, itemIndex: Int) {
        _selectedItem.value = NavGroupSelectedItem(groupIndex, itemIndex)
        _navGroups.value.getOrNull(groupIndex)?.let { group ->
            group.members.getOrNull(itemIndex)?.let { navigationItem ->
                println("Click ${group.name} :${navigationItem.label}")
            }
        }
    }


}

/*
 */
@Composable
fun FacultyLayoutBuilder2Demo() {
    val factory = remember {
        SectionFactory()
    }
    FacultyLayoutBuilder2(
        navGroups = factory.navGroups.collectAsState().value,
        onGroupClick = factory::onGroupSelect,
        onItemSelect = factory::onItemSelect,
        selectedItem = factory.selectedItem.collectAsState().value
    )

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

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun FacultyLayoutBuilder2(
    navGroups: List<NavigationGroup>,
    onGroupClick: (Int) -> Unit,
    onItemSelect: (groupIndex: Int, itemIndex: Int) -> Unit,
    selectedItem: NavGroupSelectedItem,
) {
    val scope= rememberCoroutineScope()
    val drawerController by remember {
        mutableStateOf(MyDrawerState(scope))
    }

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
            LayoutOnNoExpended3(
                contentSection = {
                    Text("Put content here")
                },
                tabSection = {
                    Text("Put tab here")
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
                drawerGroups = navGroups.map {
                    ModalDrawerGroup(
                        name = it.name,
                        members = it.members
                            .map { category ->
                                NavigationItem(
                                    label = category.label,
                                    unFocusedIcon = category.unFocusedIcon,
                                )
                            }
                    )
                },
                drawerController = drawerController,
                snackbarMessage = null,
                onDrawerGroupClick = onGroupClick,
                selectedItem = selectedItem,
                onNaigationItemClick = onItemSelect
            )
        }

    }


}