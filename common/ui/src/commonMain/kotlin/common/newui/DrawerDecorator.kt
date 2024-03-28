package common.newui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/*
 * Used to loose coupling,so that direcly this file can be copy -paste without the nav-rail dependency
Copy-paste and edit this file directly according to requirement.
since this is not a library,it is copy-pasting-editing,so to keep the client code simpler and easy to use
define the necessary thing here
 */

/**
 * This will reduce the complexity for selection and navigate when we have the group
 */
interface  Destination {
   data object None:Destination
}

class NavigationItem(
    val label: String,
    val focusedIcon: ImageVector,
    val unFocusedIcon: ImageVector = focusedIcon,
    val destination: Destination = Destination.None,
)

@Immutable
data class NavGroup(
    val label: String? = null,
    val items: List<NavigationItem>
)

// TODO(Drawer  section  -- Drawer  section -- Drawer  section -Drawer  section)
// TODO(Drawer  section  -- Drawer  section -- Drawer  section -Drawer  section)
// TODO(Drawer  section  -- Drawer  section -- Drawer  section -Drawer  section)
// TODO(Drawer  section  -- Drawer  section -- Drawer  section -Drawer  section)

@Immutable
data class ModalDrawerSheetState(
    val groups: List<NavGroup>,
    val selected: Destination? = null,
    val itemVisibilityDelay: Long? = null//if null then has no animation
)

/**
 * @param hasAnimation the sheet has animation or not
 */
data class ModelDrawerState(
    val sheetState: ModalDrawerSheetState,
    val drawerState: DrawerState,
)


/**
 * Non-default parameters: [state],[content]
 *  * has a distinct composable for drawer items without animation.
 *  * Handling both animated and non-animated scenarios within the same composable may inadvertently invoke the Animation API unnecessarily, leading to unwanted effects as the animation API executes on every frame.
 *  * It is crucial to exercise caution when dealing with animation APIs to avoid unintended calls or accidental object creation within the animation API, preventing unnecessary object creation.
 * */

/**
 * * The factory to create easily the [ModelDrawerState],so that client code
 * is short and clean enough
 * * Needed to refactor for coroutine scope
 */

class  DrawerController   (
    group: List<NavGroup>,
    itemVisibilityDelay: Long? = null,
) {
    private val _drawerState = MutableStateFlow(
      ModelDrawerState(
            sheetState = ModalDrawerSheetState(
                groups = group,
                itemVisibilityDelay = itemVisibilityDelay,
            ),
            drawerState = DrawerState(DrawerValue.Closed),
        )
    )
    val drawerState = _drawerState.asStateFlow()


    /**
     * * No Default params
     * * It a factory method that make the object creation easy
     */
    /**
     * Refactored need later
     */
    fun openDrawer() {
        _drawerState.update {
            it.copy(
                drawerState = DrawerState(DrawerValue.Open) //do not create new instance of DrawerState
                //use drawerState.open, fix the coroutine issue first,then do it
            )
        }
    }

    fun makeSelection(destination: Destination) {
        val newSheetState = drawerState.value.sheetState.copy(selected = destination)
        _drawerState.update {
            it.copy(
                sheetState = newSheetState,
                drawerState = DrawerState(DrawerValue.Closed)
            )
        }
//
//            drawerState.value.drawerState.close()
        //not replace the state every time close,because this would create new
        //Drawer State object every time,which might by expensive
        //but for some reason desktop coroutine is causes crashes,that is why we are direcly
        //creating new state,refactor it later

    }


}
@Composable
 fun ModalDrawerDecoratorNew(
    state: ModelDrawerState,
    onEvent: (DrawerSheetEvent) -> Unit,
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    val hasAnimation = state.sheetState.itemVisibilityDelay != null
    if (hasAnimation) {
        _DestinationAnimateAbleDrawer(
            state = state.sheetState,
            drawerState = state.drawerState,
            header = header,
            content = content,
            onEvent = onEvent
        )
    } else {
        _AnimationLessDrawer(
            sheetState = state.sheetState,
            drawerState = state.drawerState,
            header = header,
            content = content,
            onEvent = onEvent
        )
    }
}

@Composable
private fun _DestinationAnimateAbleDrawer(
    modifier: Modifier = Modifier,
    state: ModalDrawerSheetState,
    drawerState: DrawerState,
    onEvent: (DrawerSheetEvent) -> Unit,
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    _ModalDrawer(
        modifier = modifier,
        drawerState = drawerState,
        sheet = {
            AnimatedVisibility(
                visible = drawerState.currentValue == DrawerValue.Open,
            ) {
                _DrawerSheet(
                    state = state,
                    header = header,
                    onEvent = onEvent
                )
            }

        },
        content = content
    )

}


@Composable
private fun _AnimationLessDrawer(
    modifier: Modifier = Modifier,
    sheetState: ModalDrawerSheetState,
    drawerState: DrawerState,
    onEvent: (DrawerSheetEvent) -> Unit,
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    _ModalDrawer(
        modifier = modifier,
        drawerState = drawerState,
        sheet = {
            _DrawerSheet(
                state = sheetState,
                header = header,
                onEvent = onEvent
            )
        },
        content = content
    )

}

// TODO(Drawer SHEET section  -- Drawer SHEET section -- Drawer SHEET section -Drawer SHEET section)
// TODO(Drawer SHEET section  -- Drawer SHEET section -- Drawer SHEET section -Drawer SHEET section)
// TODO(Drawer SHEET section  -- Drawer SHEET section -- Drawer SHEET section -Drawer SHEET section)
// TODO(Drawer SHEET section  -- Drawer SHEET section -- Drawer SHEET section -Drawer SHEET section)

/**
 * * Event for the drawer sheet.
 * * Defining so that number of parameter  is reduced to sheet decorator
 * * Also it easy to add or remove new event easily,and propagate up to the client
 */
sealed interface DrawerSheetEvent {
    data class Selected(val destination: Destination) : DrawerSheetEvent
    data class Hovered(val destination: Destination) : DrawerSheetEvent
}

/**
 * * Non Default params [state],[onEvent]
 */
@Composable
private fun _DrawerSheet(
    onEvent: (DrawerSheetEvent) -> Unit,
    state: ModalDrawerSheetState,
    header: (@Composable () -> Unit)? = null,
) {
    val groups = state.groups
    val lastIndex = groups.size - 1
    ModalDrawerSheet(modifier = Modifier.width(IntrinsicSize.Max)) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
        ) {
            if (header != null) {
                header()
            }
            groups.forEachIndexed { groupNo, group ->
                group.items.forEach { item ->
                    _NavItem(
                        item = item,
                        isSelected = item.destination == state.selected,
                        visibilityDelay = state.itemVisibilityDelay,
                        onClick = {
                            onEvent(DrawerSheetEvent.Selected(item.destination))
                        }
                    )
                }
                if (groupNo != lastIndex) {
                    HorizontalDivider()
                }

            }
        }

    }
}

// TODO("Drawer Item section  -- Drawer Item section -- Drawer Item section -Drawer Item section")
// TODO("Drawer Item section  -- Drawer Item section -- Drawer Item section -Drawer Item section")
// TODO("Drawer Item section  -- Drawer Item section -- Drawer Item section -Drawer Item section")
// TODO("Drawer Item section  -- Drawer Item section -- Drawer Item section -Drawer Item section")
/**
 * Non-default parameters: [item], [isSelected], [onClick]
 *  * has a distinct composable for drawer items without animation.
 *  * Handling both animated and non-animated scenarios within the same composable may inadvertently invoke the Animation API unnecessarily, leading to unwanted effects as the animation API executes on every frame.
 *  * It is crucial to exercise caution when dealing with animation APIs to avoid unintended calls or accidental object creation within the animation API, preventing unnecessary object creation.
 * */
@Composable
private fun _NavItem(
    item: NavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    visibilityDelay: Long? = null,
) {
    if (visibilityDelay == null) //no visibility==no animation
    {
        _NavItem(
            item = item,
            isSelected = isSelected,
            onClick = onClick
        )
    } else {
        _WithAnimation(
            visibilityDelay = visibilityDelay,
        ) {
            _NavItem(
                item = item,
                isSelected = isSelected,
                onClick = onClick
            )
        }
    }

}

@Composable
private fun _NavItem(
    item: NavigationItem,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    NavigationDrawerItem(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp),
        icon = {
            Icon(
                imageVector = if (isSelected)item.focusedIcon else item.unFocusedIcon,
                contentDescription = "Nav item"
            )
        },
        label = {
            Text(
                text = item.label
            )
        },
        selected = isSelected,
        onClick = onClick,
        colors = NavigationDrawerItemDefaults.colors(
            selectedContainerColor = MaterialTheme.colorScheme.secondary,
            selectedTextColor = MaterialTheme.colorScheme.onSecondary,
            selectedIconColor = MaterialTheme.colorScheme.onSecondary,
            unselectedIconColor = MaterialTheme.colorScheme.primary,
        )
    )

}

@Composable
private fun _WithAnimation(
    visibilityDelay: Long,
    item: @Composable () -> Unit,
) {

    var visible by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(visibilityDelay)
            visible = true
            break
        }
    }
    AnimatedVisibility(
        visible = visible
    ) {
        item()
    }
}


@Composable
private fun _ModalDrawer(
    modifier: Modifier,
    drawerState: DrawerState,
    sheet: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        gesturesEnabled = true,
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = sheet,
        content = content,
    )


}


/*
 * Manage the own navRail so that does not need to copy-paste or implement the nav-rail file separately
 * Maintain can calculating the window size manually so that does not copy-paste or implement the window decorator  file separately
 */
/**
 * * Decorate the bottom bar
 * * It manage it own navRail version so that
 * * Manage it own Scaffold,since scaffold is sub compose layout so making it parent
 * as scrabble without defining it size can causes crash.
 * * But the [content] can be scrollable without any effect
 * * If you used it inside another sub compose layout such as Scaffold or Lazy List then
 * and make the parent scrollable then it can causes crash,so use modifier to define it size in that case
 * @param modifier the scaffold modifier,so that you can control the scaffold
 * @param selected is Nullable because it might possible that no destination is selected
 * * mandatory parameters: [destinations],[onDestinationSelected],[content]
 *
 */
//@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
//@Composable
//fun BottomBarToNavRailDecorator(
//    modifier: Modifier = Modifier,
//    destinations: List<NavigationItem>,
//    onDestinationSelected: (Destination) -> Unit,
//    selected: Int? = null,
//    topAppbar: @Composable () -> Unit = {},
//    content: @Composable () -> Unit,
//) {
//    val windowSize = calculateWindowSizeClass().widthSizeClass
//    val compact = WindowWidthSizeClass.Compact
//    val medium = WindowWidthSizeClass.Medium
//    val expanded = WindowWidthSizeClass.Expanded
//
//    AnimatedContent(windowSize) { window ->
//        when (window) {
//            compact -> {
//                BottomBarLayout(
//                    modifier = modifier,
//                    destinations = destinations,
//                    onItemSelected = onDestinationSelected,
//                    selected = selected,
//                    topAppbar = topAppbar,
//                    content = content
//                )
//            }
//
//            medium, expanded -> {
//                NavRailLayout(
//                    destinations = destinations,
//                    onItemSelected = onDestinationSelected,
//                    selected = selected,
//                    topAppbar = topAppbar,
//                    content = content
//                )
//            }
//
//        }
//    }
//
//
//}

/*
 * Used to loose coupling,so that direcly this file can be copy -paste without the nav-rail dependency
 */
/**
 * * It does only the information that need to NavigationItem
 * * It does not hold any extra information
 * * Mandatory params : [label] , [focusedIcon]
 * Storing the destination here,to reduce the client reprehensibly to figure out
 * which destination is cliecked
 */

//
//@Composable
//private fun BottomBarLayout(
//    modifier: Modifier = Modifier,
//    destinations: List<NavigationItem>,
//    onItemSelected: (Destination) -> Unit,
//    selected: Int? = null,
//    topAppbar: @Composable () -> Unit = {},
//    content: @Composable () -> Unit,
//) {
//    Scaffold(
//        modifier = modifier,
//        topBar = topAppbar,
//        bottomBar = {
//            _BottomNavBar(
//                destinations = destinations,
//                selected = selected,
//                onDestinationSelected = onItemSelected
//            )
//        }
//    ) { scaffoldPadding ->
//        Box(Modifier.padding(scaffoldPadding)) {
//            content()
//        }
//
//    }
//}
//
//@Composable
//private fun NavRailLayout(
//    modifier: Modifier = Modifier,
//    destinations: List<NavigationItem>,
//    onItemSelected: (Destination) -> Unit,
//    selected: Int? = null,
//    topAppbar: @Composable () -> Unit = {},
//    content: @Composable () -> Unit,
//) {
//    Row(modifier = modifier) {
//        _NavRail(
//            destinations = destinations,
//            selected = selected,
//            onItemSelected = onItemSelected
//        )
//        Scaffold(
//            modifier = Modifier,
//            topBar = topAppbar,
//        ) { scaffoldPadding ->
//            Box(Modifier.padding(scaffoldPadding)) { content() }//takes the remaining space,after the NavRail takes place
//        }
//
//    }
//
//
//}
//
///**
// * Used to loose coupling,so that direcly this file can be copy -paste without the nav-rail dependency
// */
//@Composable
//private fun _NavRail(
//    modifier: Modifier = Modifier,
//    destinations: List<NavigationItem>,
//    onItemSelected: (Destination) -> Unit,
//    selected: Int? = null,
//) {
//    NavigationRail(
//        modifier = modifier,
//        containerColor = MaterialTheme.colorScheme.surface
//    ) {
//        Surface(
//            modifier = Modifier.fillMaxHeight(),
//            tonalElevation = 3.dp //same as bottom bar elevation
//        ) {
//            Column(Modifier.width(IntrinsicSize.Max)) {
//                destinations.forEachIndexed { index, navigationItem ->
//                    //Using Drawer item so place the icon and label side by side
//                    NavigationDrawerItem(
//                        colors = NavigationDrawerItemDefaults.colors(
//                            selectedContainerColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f),
//                            selectedIconColor = MaterialTheme.colorScheme.secondary,
//                            selectedTextColor = MaterialTheme.colorScheme.contentColorFor(
//                                MaterialTheme.colorScheme.tertiary.copy(alpha = 0.7f)
//                            ),
//
//                            unselectedIconColor = MaterialTheme.colorScheme.primary,//because they are clickable button,so high importance
//
//
//                            //   se = MaterialTheme.colorScheme.onSecondary,
//
//                        ),
//                        modifier = Modifier.padding(4.dp),
//                        icon = {
//                            Icon(
//                                navigationItem.focusedIcon,
//                                contentDescription = null
//                            )
//                        },
//                        label = {
//                            Text(
//                                text = navigationItem.label
//                            )
//                        },
//                        selected = selected == index,
//                        onClick = { onItemSelected(navigationItem.destination) },
//                        shape = RoundedCornerShape(8.dp)
//                    )
//                }
//            }
//        }
//
//
//    }
//}
//
///**
// * Used to loose coupling,so that direcly this file can be copy -paste without BottomBar from another file
// */
//
//@Composable
//private fun _BottomNavBar(
//    modifier: Modifier = Modifier,
//    destinations: List<NavigationItem>,
//    selected: Int? = null,
//    onDestinationSelected: (Destination) -> Unit,
//) {
//    NavigationBar(
//        modifier = modifier,
//    ) {
//        destinations.forEachIndexed { index, destination ->
//            NavigationBarItem(
//                selected = selected == index,
//                onClick = {
//                    onDestinationSelected(destination.destination)
//                },
//                label = {
//                    Text(text = destination.label)
//                },
//                alwaysShowLabel = false,
//                icon = {
//                    BadgedBox(
//                        badge = {
//                            if (destination.badge != null) {
//                                Badge {
//                                    Text(text = destination.badge.toString())
//                                }
//                            }
//                        }
//                    ) {
//                        (if (index == selected) {
//                            destination.focusedIcon
//                        } else destination.unFocusedIcon).let {
//                            Icon(
//                                imageVector = it,
//                                contentDescription = destination.label
//                            )
//                        }
//                    }
//                },
//                colors = NavigationBarItemDefaults.colors().copy(
//                    selectedIconColor = MaterialTheme.colorScheme.secondary,
//                    selectedIndicatorColor = MaterialTheme.colorScheme.onSecondary,
//                    unselectedIconColor = MaterialTheme.colorScheme.primary,//because they are clickable button,so high importance
//
//                )
//            )
//        }
//    }
//
//
//}