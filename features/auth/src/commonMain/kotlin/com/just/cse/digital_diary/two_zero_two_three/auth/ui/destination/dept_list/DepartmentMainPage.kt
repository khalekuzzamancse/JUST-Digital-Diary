package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.dept_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.data.repository.Employee
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.bottom_navigation.BottomNavigationBar
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destination.employee_list.EmployeeList
import kotlin.random.Random

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
    modifier: Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        DepartmentFromChairmen()

    }

}

@Composable
fun DepartmentFromChairmen() {
    Column(
        modifier = Modifier
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
                .align(Alignment.CenterHorizontally),
        )
        Text(
            text = "MESSAGE FROM THE CHAIRMAN",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text="Welcome to the Department of Biomedical Engineering (BME) " +
                    "at Jashore University of Science and Technology (JUST). Our department was founded in 2016 and we want" +
                    " to create an environment of educational excellence, both in academic and research." +
                    "You’ll find a lively academic atmosphere in our department—including our faculty members, amiable staffs and specifically charming undergraduate students. Our teachers take pleasure in conveying their knowledge and intellectual " +
                    "passion to students. They also encourage students in thoughtful activities and ",
            style = MaterialTheme.typography.labelMedium
        )


    }


}

@Composable
fun DepartmentTeacherListDestinationContent(
    modifier: Modifier = Modifier,
    teachers: List<Employee>,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        EmployeeList(
            employees = teachers
        )


    }


}

@Composable
fun DepartmentStaffListDestinationContent(
    modifier: Modifier = Modifier,
    staffs: List<Employee>,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        EmployeeList(
            employees = staffs
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