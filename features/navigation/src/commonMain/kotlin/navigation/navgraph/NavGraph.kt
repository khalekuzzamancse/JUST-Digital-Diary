package navigation.navgraph

import academic.ui.AcademicModuleEvent
import academic.ui.AcademicNavGraph
import administration.ui.public_.AdminEmployeeListEvent
import administration.ui.public_.AdministrationRoute
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import common.ui.NavAnimations
import miscellaneous.MiscFeatureEvent
import miscellaneous.ui.AboutUsRoute
import miscellaneous.ui.eventGallery.EventsRoute
import miscellaneous.ui.home.HomeRoute
import miscellaneous.ui.vcmessage.MessageFromVCRoute
import navigation.AppEvent
import navigation.NavigationFactory
import navigation.component.NavDestination
import profile.presentationlogic.ProfileEvent
import schedule.ui.public_.ViewClassScheduleScreen
import schedule.ui.public_.ViewExamScheduleScreen


@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    onEvent: (AppEvent) -> Unit,
    openDrawerRequest: () -> Unit,
    onNavigateBackRequest: () -> Unit,
    startDestination: String,
    isNavRailMode: Boolean,
    navController: NavHostController,
    navigateToProfile: () -> Unit,
    onMiscFeatureEvent: (MiscFeatureEvent) -> Unit,
    onAdminEvent: (ProfileEvent) -> Unit,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = { NavAnimations.Enter.scaleIn() },
        exitTransition = { NavAnimations.Exit.scaleOut() },
        popEnterTransition = { NavAnimations.PopEnter.scaleIn() },
        popExitTransition = { NavAnimations.PopExit.scaleOut() }
    ) {
//        navController.adminNavGraph()


        ///
        composable(NavDestination.Home.route) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode,
                actions = {
                    IconButton(
                        onClick = navigateToProfile
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Profile",
                            tint = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            ) {
                HomeRoute(
                    onEvent = onMiscFeatureEvent
                )
            }

        }

        composable(NavDestination.FacultyList.route) {
            AcademicNavGraph(
                onEvent = { event ->
                    toAppEvent(event)?.let(onEvent)
                },
                navigationIcon = if (!isNavRailMode) {
                    {
                        _MenuIcon(openDrawerRequest)
                    }
                } else null,
                token = NavigationFactory.token.value
            )
        }

        composable(NavDestination.AdminOffice.route) {
            AdministrationRoute(
                token = NavigationFactory.token.value,
                onEvent = {
                    it.toAppEvent()?.let { it1 -> onEvent(it1) }
                },
                navigationIcon = if (!isNavRailMode) {
                    {
                        _MenuIcon(openDrawerRequest)
                    }
                } else null
            )
        }
        composable(NavDestination.Profile.route) {
          ProfileNavGraph(
              navigationIcon = if (!isNavRailMode) {
                  {
                      _MenuIcon(openDrawerRequest)
                  }
              } else null
          )
        }
        composable(NavDestination.ClassSchedule.route) {
            _DrawerIconDecorator(
                isNavRailMode = isNavRailMode,
                onMenuIconClick = openDrawerRequest,
            ) {
                ViewClassScheduleScreen()
            }

        }
        composable(NavDestination.ExamSchedule.route) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                ViewExamScheduleScreen()
            }
        }


        composable(NavDestination.MessageFromVC.route) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                MessageFromVCRoute(
                    token = NavigationFactory.token.value
                )
            }

        }
        composable(NavDestination.EventGallery.route) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                EventsRoute(
                    token = NavigationFactory.token.value
                )
            }

        }
        composable(NavDestination.AboutUs.route) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                AboutUsRoute()
            }

        }

    }
}


private fun toAppEvent(event: AcademicModuleEvent): AppEvent? {
    val ev: AppEvent? = when (event) {
        is AcademicModuleEvent.CallRequest -> AppEvent.CallRequest(event.number)
        is AcademicModuleEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
        is AcademicModuleEvent.EmailRequest -> AppEvent.EmailRequest(event.email)

        else -> {
            null
        }
    }
    return ev

}

private fun AdminEmployeeListEvent.toAppEvent(): AppEvent? {
    val ev: AppEvent? = when (this) {
        is AdminEmployeeListEvent.CallRequest -> AppEvent.CallRequest(this.number)
        is AdminEmployeeListEvent.MessageRequest -> AppEvent.MessageRequest(this.number)
        is AdminEmployeeListEvent.EmailRequest -> AppEvent.EmailRequest(this.email)
        else -> {
            null
        }
    }
    return ev
}


@Composable
private fun _MenuIcon(
    onClick: () -> Unit
) {
    IconButton(onClick = onClick) {
        Icon(Icons.Filled.Menu, contentDescription = "back")
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun _DrawerIconDecorator(
    isNavRailMode: Boolean,
    onMenuIconClick: () -> Unit,
    actions: @Composable RowScope.() -> Unit = {},
    content: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    if (!isNavRailMode) {
                        IconButton(
                            onClick = onMenuIconClick
                        ) {
                            Icon(Icons.Default.Menu, contentDescription = "Drawer Icon")
                        }
                    }

                },
                actions = {
                    actions()
                }
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(it).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }

    }
}
