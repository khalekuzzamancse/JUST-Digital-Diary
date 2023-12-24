package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.NavigationRailLayout
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.compact
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.expanded
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.medium
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.DepartmentList
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.EmployeeList
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.EmployeeListDemo
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.administrativeOffices
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.depts
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.employees
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.faculties
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class FacultyLayoutFactoryState(
    private val scope: CoroutineScope,
    private val destination: List<NavigationItem>,
    private val onNavigationRequest: (String) -> Unit,
) {
    val drawerState = DrawerState(DrawerValue.Closed)
    fun openDrawer() {
        scope.launch {
            drawerState.open()
        }
    }


    private val _drawerGroup = MutableStateFlow(
        listOf(
            ModalDrawerGroup("Faculty Members", members = destination, isVisible = false),
            ModalDrawerGroup("Admin Office", members = administrativeOffices, isVisible = false)
        )
    )
    val drawerGroups = _drawerGroup.asStateFlow()
    fun onDrawerGroupClick(index: Int) {
        println("Group clicked: ${index}")
        _drawerGroup.update { groups ->
            groups.mapIndexed { i, group ->
                if (i == index) group.copy(isVisible = !group.isVisible) else group
            }

        }
    }


    fun closeDrawer() {
        scope.launch {
            drawerState.close()
        }
    }

    fun onDrawerItemClick(route: String) {
        onNavigationRequest(route)
    }

    fun onNavRailItemSelected(item: Int) {
        destination.getOrNull(item)?.let {
            onNavigationRequest(it.route)
        }
    }
}

//stateful
@Composable
fun FacultyLayoutFactoryDemo() {
    val scope = rememberCoroutineScope()
    val factory = remember {
        FacultyLayoutFactoryState(
            scope = scope,
            destination = faculties,
            onNavigationRequest = {}
        )
    }
    FacultyLayoutFactory(
        factory = factory,
        destination = faculties,
        contentSection = {
            EmployeeListDemo()
        },
        tabDestination = depts.map {
            NavigationItem(
                label = it.fullName,
                unFocusedIcon = Icons.Default.School
            )
        },
    )
}

@Composable
fun FacultyLayoutFactory(
    factory: FacultyLayoutFactoryState,
    destination: List<NavigationItem>,
    tabDestination: List<NavigationItem>,
    snackbarMessage: String? = null,
    contentSection: @Composable ((Modifier) -> Unit),
) {
    FacultyLayoutBuilder(
        destination = destination,
        drawerState = factory.drawerState,
        drawerGroups = factory.drawerGroups.collectAsState().value,
        onDrawerItemClick = factory::onDrawerItemClick,
        onDrawerCloseRequested = factory::closeDrawer,
        snackbarMessage = snackbarMessage,
        contentSection = contentSection,
        onDrawerGroupClick = factory::onDrawerGroupClick,
        tabSection = {
            TabSection(
                modifier = it,
                tabDestination = tabDestination,
                onNavigationIconClick = factory::openDrawer
            )
        },
        onNavRailItemSelected = factory::onNavRailItemSelected
    )
}

@Composable
fun TabSection(
    modifier: Modifier,
    tabDestination: List<NavigationItem>,
    onNavigationIconClick: () -> Unit
) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = tabDestination.map { it.label }

    Surface(
        modifier = modifier,
        shadowElevation = 10.dp,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            Modifier.background(
                MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            IconButton(
                onClick = onNavigationIconClick,
            ) {
                Icon(
                    Icons.Filled.Menu, null
                )
            }
            ScrollableTabRow(
                selectedTabIndex = tabIndex,
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
        }

    }

}


//Builder will maintain the different layout on based on screen size.
//it will not expose which layout is it using
//need to refactor i
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun FacultyLayoutBuilder(
    drawerState: DrawerState,
    onDrawerItemClick: (String) -> Unit,
    onDrawerCloseRequested: () -> Unit,
    onNavRailItemSelected: (Int) -> Unit,
    destination: List<NavigationItem>,
    drawerGroups: List<ModalDrawerGroup>,
    snackbarMessage: String? = null,
    onDrawerGroupClick: (Int) -> Unit = {},
    contentSection: @Composable ((Modifier) -> Unit),
    tabSection: @Composable ((Modifier) -> Unit)
) {
    when (calculateWindowSizeClass().widthSizeClass) {
        expanded -> {
            NavigationRailLayout(
                selectedDestinationIndex = 0,
                destinations = destination,
                onNavRailItemSelected = onNavRailItemSelected,
            ) {
                FacultyLayoutOnExpanded()
            }
        }

        compact, medium -> {
            LayoutOnNoExpended(
                contentSection = contentSection,
                tabSection = tabSection,
                modifier = Modifier,
                drawerGroups = drawerGroups,
                drawerState = drawerState,
                onDrawerItemClick = onDrawerItemClick,
                onDrawerCloseRequested = onDrawerCloseRequested,
                snackbarMessage = snackbarMessage,
                onDrawerGroupClick = onDrawerGroupClick
            )
        }

    }
}

//Complete stateless composable
@Composable
fun LayoutOnNoExpended(
    modifier: Modifier,
    drawerState: DrawerState,
    onDrawerItemClick: (String) -> Unit,
    onDrawerCloseRequested: () -> Unit,
    drawerGroups: List<ModalDrawerGroup>,
    snackbarMessage: String? = null,
    onDrawerGroupClick: (Int) -> Unit = {},
    contentSection: @Composable ((Modifier) -> Unit),
    tabSection: @Composable ((Modifier) -> Unit)

) {
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    snackbarMessage?.let { message ->
        scope.launch {
            hostState.showSnackbar(message)
        }
    }
    ModalDrawer(
        modifier = Modifier,
        drawerState = drawerState,
        sheet = {
            HomeScreenDrawerSheet(
                groups = drawerGroups,
                onGroupClicked = onDrawerGroupClick,
                onItemClicked = { _, _ ->
                    onDrawerItemClick("")
                    onDrawerCloseRequested()
                }
            )
        },
        content = {
            //Scaffold used top level so that for any screen snack-bar can be used
            Scaffold(
                modifier = modifier,
                snackbarHost = { SnackbarHost(hostState = hostState) },
                topBar = { tabSection(Modifier) },

                ) { scaffoldPadding ->
                Column(Modifier.padding(scaffoldPadding)) {
                    Spacer(Modifier.height(16.dp))
                    contentSection(Modifier)
                }
            }
        },

        )
}

/*

this should handle manage the it component state
it will tell only which nav group is currently selected,so that you can pass down the
group members of of the selected or clicked group.
it will manage the drawer itself,the drawer responsibility should not go to its parent because
it parent does need to know how to display the the destination,
so the destination can be shown in the different style based on the screen size.

it will ask for for which
it responsibility:
 maintain the navigation(drawer here) since it not expanded screen.
 Inform current which group is clicked so that parent pass down the group member,or update the groups or group members.
 inform to which group item is clicked,so that parent pass down the content or tab means
 it can pass down the content associated with current selected or clicked members of the group.

 Responsibility:
 Keep drawer state
 Open drawer
 close drawer
 notify which group is clicked
 notify which group which member is clicked

 */
class MyDrawerState(
    private val scope: CoroutineScope,
) {
    val drawerState = DrawerState(DrawerValue.Open)
    fun openDrawer() {
        scope.launch {
            drawerState.open()
        }
    }

    fun closeDrawer() {
        scope.launch {
            drawerState.close()
        }
    }
}
@Composable
fun LayoutOnNoExpended3(
    modifier: Modifier,
    drawerController:MyDrawerState,
    onNaigationItemClick: (groupIndex:Int, itemIndex:Int) -> Unit,
    selectedItem:NavGroupSelectedItem,
    drawerGroups: List<ModalDrawerGroup>,
    snackbarMessage: String? = null,
    onDrawerGroupClick: (Int) -> Unit = {},
    contentSection: @Composable ((Modifier) -> Unit),
    tabSection: @Composable ((Modifier) -> Unit)

) {

    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    snackbarMessage?.let { message ->
        scope.launch {
            hostState.showSnackbar(message)
        }
    }
    ModalDrawer(
        modifier = Modifier,
        drawerState = drawerController.drawerState,
        sheet = {
            HomeScreenDrawerSheet(
                selectedItemIndex = selectedItem,
                groups = drawerGroups,
                onGroupClicked = onDrawerGroupClick,
                onItemClicked = { groupIndex, index ->
                    onNaigationItemClick(groupIndex,index)
                   drawerController.closeDrawer()
                }
            )
        },
        content = {
            //Scaffold used top level so that for any screen snack-bar can be used
            Scaffold(
                modifier = modifier,
                snackbarHost = { SnackbarHost(hostState = hostState) },
                topBar = { tabSection(Modifier) },

                ) { scaffoldPadding ->
                Column(Modifier.padding(scaffoldPadding)) {
                    Spacer(Modifier.height(16.dp))
                    contentSection(Modifier)
                }
            }
        },

        )
}
@Composable
fun LayoutOnNoExpended2(
    modifier: Modifier,
    drawerState: DrawerState,
    onDrawerItemClick: (groupIndex:Int,itemIndex:Int) -> Unit,
    onDrawerCloseRequested: () -> Unit,
    selectedItem:NavGroupSelectedItem,
    drawerGroups: List<ModalDrawerGroup>,
    snackbarMessage: String? = null,
    onDrawerGroupClick: (Int) -> Unit = {},
    contentSection: @Composable ((Modifier) -> Unit),
    tabSection: @Composable ((Modifier) -> Unit)

) {
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    snackbarMessage?.let { message ->
        scope.launch {
            hostState.showSnackbar(message)
        }
    }
    ModalDrawer(
        modifier = Modifier,
        drawerState = drawerState,
        sheet = {
            HomeScreenDrawerSheet(
                selectedItemIndex = selectedItem,
                groups = drawerGroups,
                onGroupClicked = onDrawerGroupClick,
                onItemClicked = { groupIndex, index ->
                    onDrawerItemClick(groupIndex,index)
                    onDrawerCloseRequested()
                }
            )
        },
        content = {
            //Scaffold used top level so that for any screen snack-bar can be used
            Scaffold(
                modifier = modifier,
                snackbarHost = { SnackbarHost(hostState = hostState) },
                topBar = { tabSection(Modifier) },

                ) { scaffoldPadding ->
                Column(Modifier.padding(scaffoldPadding)) {
                    Spacer(Modifier.height(16.dp))
                    contentSection(Modifier)
                }
            }
        },

        )
}

/*
In Expanded:
NavRail=Faculty Types,
Pane 1=Department
Pane 2=Teacher
 */
@Composable
fun FacultyLayoutOnExpanded(

) {
    Row(Modifier.fillMaxWidth()) {
        DepartmentList(modifier = Modifier.weight(1f), departments = depts)
        EmployeeList(
            modifier = Modifier.weight(1f),
            employees = employees
        )
    }


}

/*
Faculty in the drawer


 */