package com.just.cse.digital_diary.two_zero_two_three.auth.ui.faculty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ScreenWithDrawer
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
            ModalDrawerGroup("Faculty Members", members =  destination,isVisible = false),
            ModalDrawerGroup("Admin Office", members = administrativeOffices,isVisible = false)
        )
    )
    val drawerGroups = _drawerGroup.asStateFlow()
    fun onDrawerGroupClick(index: Int) {
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
        drawerHeader = {
            Text("This is drawer header")
        },
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

//Stateless
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
    drawerHeader: @Composable (ColumnScope.(Modifier) -> Unit) = {},
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
                bottomTabSection = tabSection,
                modifier = Modifier,
                destination = drawerGroups,
                drawerState = drawerState,
                onDrawerItemClick = onDrawerItemClick,
                onDrawerCloseRequested = onDrawerCloseRequested,
                snackbarMessage = snackbarMessage,
                drawerHeader = drawerHeader,
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
    destination: List<ModalDrawerGroup>,
    snackbarMessage: String? = null,
    drawerHeader: @Composable (ColumnScope.(Modifier) -> Unit) = {},
    onDrawerGroupClick: (Int) -> Unit = {},
    contentSection: @Composable ((Modifier) -> Unit),
    bottomTabSection: @Composable ((Modifier) -> Unit)

) {
    val hostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    snackbarMessage?.let { message ->
        scope.launch {
            hostState.showSnackbar(message)
        }
    }
    BoxWithConstraints(modifier) {
        Row(modifier = Modifier.fillMaxSize()) {
            ScreenWithDrawer(
                drawerState = drawerState,
                destination = destination,
                closeDrawer = onDrawerCloseRequested,
                onDrawerItemClick = onDrawerItemClick,
                header = drawerHeader,
                onGroupClicked = onDrawerGroupClick,
                content = {
                    //Scaffold used top level so that for any screen snack-bar can be used
                    Scaffold(
                        modifier = modifier,
                        snackbarHost = {
                            SnackbarHost(hostState = hostState)
                        },
                        topBar = {
                            bottomTabSection(Modifier)
                        },

                        ) { scaffoldPadding ->
                        Column(Modifier.padding(scaffoldPadding)) {
                            Spacer(Modifier.height(16.dp))
                            contentSection(Modifier)
                        }
                    }

                }

            )
        }

    }
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