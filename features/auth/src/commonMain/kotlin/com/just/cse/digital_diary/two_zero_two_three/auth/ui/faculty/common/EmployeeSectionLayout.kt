package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.NavigationRailLayout
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.compact
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.expanded
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.medium
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.DepartmentList
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.EmployeeList
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.EmployeeListDemo
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty.TabSection
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
Taking the tab section as slot element so that is
does not need to handle the tab element,it is responsible for the layout the tab
and the content,it does not know and should know what the content are and how to
handle them.
however as a result by passing the different topSection handle you can extended it behaviour,
do npt need to modify it for different type of content and different type of tab or top section.
so you can pass top app bar,to top  tab as a handler.
if you need to more functionality then do not direcly modify it ,instead of wrap it
inside the another composable and add the additional behavior.

 */

@Composable
fun LayoutOnNoExpended(
    navigationGroups: List<NavigationGroup>,
    selectedItem: NavGroupSelectedItem,
    onNavigationGroupClicked: (Int) -> Unit = {},
    onNavigationItemClick: (groupIndex: Int, itemIndex: Int) -> Unit,
    topSection: @Composable ((Modifier) -> Unit),
    content: @Composable (Modifier) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val drawerController by remember {
        mutableStateOf(MyDrawerState(scope))
    }
    ModalDrawer(
        modifier = Modifier,
        drawerState = drawerController.drawerState,
        sheet = {
            HomeScreenDrawerSheet(
                selectedItemIndex = selectedItem,
                groups = navigationGroups.map {
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
                onGroupClicked = onNavigationGroupClicked,
                onItemClicked = { groupIndex, index ->
                    onNavigationItemClick(groupIndex, index)
                    drawerController.closeDrawer()
                }
            )
        },
        content = {
            Scaffold(
                modifier = Modifier,
                topBar = { topSection(Modifier) },
            ) { scaffoldPadding ->
                Column(Modifier.padding(scaffoldPadding)) {
                    content(Modifier)
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

 //Additional
 it should expose which navigation component it uses to its parent because it parent totally trust him
 so in future we can change to a different navigation component so we do should expose
 any information related to the navigation component to it parent
 that is why takes the navigation group instead of direcly taking drawer components

 //Since this is handling Non Expanded screen so it does not have much screen width
that is why it will  not decide for which which destination which conten wil be shown.
it also not decide how the nested destination or group members child destination(if present) should handle
that is why it takes the a single content lambda that,
it just responsive for placing the top most destination(drawer group members)

/
it  okay to maintain the drawer state here,because in case of orientation change
only the drawer state (open or closed) might be affected,the which item is selected
or which group is selected will  not affect,that is why keep the drawer state internal here
so that it parent does not know about this state,
because it unnecessary to inform the parent about the drawer because in future
we might replace the drawer with other navigation components.

//however you may extend the functionality of this by wrapping it inside
another composable such as if you want expanded with bottom bar,exapnaded with tabs,
so on,,,
but dot not modify it direcly.



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
fun LayoutOnNoExpended(
    navigationGroups: List<NavigationGroup>,
    selectedItem: NavGroupSelectedItem,
    onNavigationGroupClicked: (Int) -> Unit = {},
    onNavigationItemClick: (groupIndex: Int, itemIndex: Int) -> Unit,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val drawerController by remember {
        mutableStateOf(MyDrawerState(scope))
    }

    ModalDrawer(
        modifier = Modifier,
        drawerState = drawerController.drawerState,
        sheet = {
            HomeScreenDrawerSheet(
                selectedItemIndex = selectedItem,
                groups = navigationGroups.map {
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
                onGroupClicked = onNavigationGroupClicked,
                onItemClicked = { groupIndex, index ->
                    onNavigationItemClick(groupIndex, index)
                    drawerController.closeDrawer()
                }
            )
        },
        content = content,

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