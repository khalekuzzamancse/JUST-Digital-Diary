package navigation

import academic.ui.AcademicModuleEvent
import academic.ui.public_.AcademicRoute
import administration.ui.public_.AdminEmployeeListEvent
import administration.ui.public_.AdministrationRoute
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
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
import miscellaneous.MiscFeatureEvent
import miscellaneous.ui.AboutUsRoute
import miscellaneous.ui.eventGallery.EventsRoute
import miscellaneous.ui.home.HomeRoute
import miscellaneous.ui.vcmessage.MessageFromVCRoute
import profile.ui.ProfileNavHost
import schedule.ui.ui.public_.ViewClassScheduleScreen
import schedule.ui.ui.public_.ViewExamScheduleScreen


@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    onEvent: (AppEvent) -> Unit,
    openDrawerRequest: () -> Unit,
    onBackPressed: () -> Unit,
    startDestination: String,
    isNavRailMode: Boolean,
    navController: NavHostController,
    onMiscFeatureEvent: (MiscFeatureEvent) -> Unit,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            scaleIn(initialScale = 0.8f, animationSpec = tween(700)) + fadeIn(
                animationSpec = tween(
                    700
                )
            )
        },
        exitTransition = {
            scaleOut(
                targetScale = 1.1f,
                animationSpec = tween(700)
            ) + fadeOut(animationSpec = tween(700))
        },
        popEnterTransition = {
            scaleIn(initialScale = 1.2f, animationSpec = tween(700)) + fadeIn(
                animationSpec = tween(
                    700
                )
            )
        },
        popExitTransition = {
            scaleOut(
                targetScale = 0.8f,
                animationSpec = tween(700)
            ) + fadeOut(animationSpec = tween(700))
        }
    ) {

        composable(GraphRoutes.HOME) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode,
                actions = {
                    IconButton(
                        onClick = {
                            try {
                                navController.navigate(GraphRoutes.PROFILE)
                            } catch (_: Exception) {

                            }
                        }
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

        composable(GraphRoutes.ACADEMIC_FEATURES) {
            AcademicRoute(
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

        composable(GraphRoutes.ADMIN_OFFICE_FEATURE) {
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
        composable(GraphRoutes.PROFILE) {
            ProfileNavHost(
                token = NavigationFactory.token.value,
                navigationIcon = if (!isNavRailMode) {
                    {
                        _MenuIcon(openDrawerRequest)
                    }
                } else null
            )
        }
        composable(GraphRoutes.CLASS_SCHEDULE_VIEWER) {
            _DrawerIconDecorator(
                isNavRailMode = isNavRailMode,
                onMenuIconClick = openDrawerRequest,
            ) {
                ViewClassScheduleScreen()
            }

        }
        composable(GraphRoutes.EXAM_SCHEDULE_VIEWER) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                ViewExamScheduleScreen()
            }
        }


        composable(GraphRoutes.VC_MESSAGES) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                MessageFromVCRoute(
                    token = NavigationFactory.token.value
                )
            }

        }
        composable(GraphRoutes.EVENT_GALLERY) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                EventsRoute(
                    token = NavigationFactory.token.value
                )
            }

        }
        composable(GraphRoutes.ABOUT_US) {
            _DrawerIconDecorator(
                onMenuIconClick = openDrawerRequest,
                isNavRailMode = isNavRailMode
            ) {
                AboutUsRoute()
            }

        }

//        composable(GraphRoutes.NOTES_FEATURE) {
//            NotesFeatureNavGraph.Graph(
//                onExitRequest = openDrawerRequest,
//                onBackPressed = onBackPressed
//            )
//        }
//        composable(GraphRoutes.SEARCH) {
//            SearchFeatureNavGraph.Graph(
//                onEvent = { event ->
//                    if (event is SearchFeatureEvent.ExitRequest)
//                        openDrawerRequest()
//                    toAppEvent(event)?.let(onEvent)
//                },
//                onBackPress = onBackPressed
//            )
//        }


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

//private fun toAppEvent(event: SearchFeatureEvent): AppEvent? {
//    val ev: AppEvent? = when (event) {
//        is SearchFeatureEvent.CallRequest -> AppEvent.CallRequest(event.number)
//        is SearchFeatureEvent.MessageRequest -> AppEvent.MessageRequest(event.number)
//        is SearchFeatureEvent.EmailRequest -> AppEvent.EmailRequest(event.email)
//        else -> null
//    }
//    return ev
//
//}

object GraphRoutes {
    const val ACADEMIC_FEATURES = "FacultyFeatureNavGraph.ROUTE"
    const val ADMIN_OFFICE_FEATURE = "AdminOfficeFeatureNavGraph.ROUTE"
    const val HOME = "HOME_ROUTE"
    const val VC_MESSAGES = "VC_MESSAGES_ROUTE"
    const val ABOUT_US = "About us route"
    const val NOTES_FEATURE = "NotesFeatureNavGraph.ROUTE"
    const val SEARCH = "Search"
    const val CLASS_SCHEDULE_VIEWER = "CLASS_SCHEDULE"
    const val EXAM_SCHEDULE_VIEWER = "EXAM_SCHEDULE"
    const val EVENT_GALLERY = "Event Gallery"
    const val PROFILE = "ProfileRoute"

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