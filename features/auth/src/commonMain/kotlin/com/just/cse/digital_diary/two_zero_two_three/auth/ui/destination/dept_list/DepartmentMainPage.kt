package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.dept_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SupervisorAccount
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.SupervisorAccount
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.Department
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.bottom_navigation.BottomNavigationBar

val departmentSubDestinations = listOf(
    NavigationItem(
        key = 0,
        label = "Home",
        unFocusedIcon = Icons.Outlined.Home,
        focusedIcon = Icons.Filled.Home,

        ),
    NavigationItem(
        key = 1,
        label = "Teachers",
        unFocusedIcon = Icons.Outlined.SupervisorAccount,
        focusedIcon = Icons.Filled.SupervisorAccount,
        badge = "20"
    ),
    NavigationItem(
        key = 2,
        label = "Staffs",
        unFocusedIcon = Icons.Outlined.Work,
        focusedIcon = Icons.Filled.Work,
        badge = "16"
    ),

    )

/*
All the department top and the bottom bar is fixed.
 */



@Composable
fun DepartmentHomeDestinationContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "This is Home Page",
            style = MaterialTheme.typography.headlineSmall
        )

    }

}

@Composable
fun DepartmentTeacherListDestinationContent(
    modifier: Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Show the list of teachers",
            style = MaterialTheme.typography.headlineSmall
        )


    }


}

@Composable
fun DepartmentStaffListDestinationContent(
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = "Show the list of staff members",
            style = MaterialTheme.typography.headlineSmall
        )

    }

}


@Composable
fun <T> TopNBottomBarDecorator(
    topBarTitle: String,
    topNavigationIcon: ImageVector? = null,
    onNavigationIconClick: () -> Unit,
    bottomDestinations: List<NavigationItem<T>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
    content: @Composable (Modifier) -> Unit,
) {
    Scaffold(
        topBar = {
            DepartmentScreenTopAppbar(
                title = topBarTitle,
                navigationIcon = topNavigationIcon,
                onNavigationIconClick = onNavigationIconClick
            )
        },
        bottomBar = {
            BottomNavigationBar(
                destinations = bottomDestinations,
                selectedDestinationIndex = selectedDestinationIndex,
                onDestinationSelected = onDestinationSelected
            )
        }
    ) { scaffoldPadding ->
        content(Modifier.padding(scaffoldPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DepartmentScreenTopAppbar(
    modifier: Modifier = Modifier,
    title: String,
    navigationIcon: ImageVector? = null,
    onNavigationIconClick: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (navigationIcon != null) {
                IconButton(
                    onClick = onNavigationIconClick
                ) {
                    Icon(
                        navigationIcon, null
                    )
                }
            }

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
        )

    )

}