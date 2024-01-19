@file:Suppress("OPT_IN_USAGE_FUTURE_ERROR")

package com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.faculties

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.faculty.faculty.navigation.local_destinations.home.HomeContent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.bottom_sheet.handler.BottomSheetHandlerImp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemProps
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout

@Composable
internal fun FacultiesScreen(
    onDepartmentNavigationRequest: (String) -> Unit,
) {
    val viewModel = remember {
        ViewModel(
            onDepartmentNavigationRequest = onDepartmentNavigationRequest
        )
    }
    val facultiesDestinations = viewModel.faculties.collectAsState().value.map {
        NavigationItemInfo2(
            label = it.name,
            iconText = "FCT",
            key = it.id
        )
    }
    val departmentsDestinations = viewModel.selectedFacultyDepartments.collectAsState().value.map {
        NavigationItemInfo2(
            label = it.name,
            iconText = "FCT",
            key = it.id
        )
    }

    val departmentsList: @Composable () -> Unit = @Composable {
        AnimatedContent(
            targetState = departmentsDestinations //animate on different faculty department list shown
        ) {
            DepartmentsDestination(
                modifier = Modifier,
                enableBackNavigation = true,
                title = "Departments List",
                destinations = departmentsDestinations,
                onDestinationSelected = viewModel::onDepartmentSelected,
                selectedDestinationIndex = viewModel.selectedDepartment.collectAsState().value,
                onDismissRequest = viewModel::onClearDepartmentSelection
            )
        }
    }

    val bottomSheetHandler = remember { BottomSheetHandlerImp() }

    WindowSizeDecorator(
        onCompact = {
            CompactScreenLayout(
                facultyDestinations = {
                    FacultiesDestinations(
                        modifier = Modifier,
                        destinations = facultiesDestinations,
                        onDestinationSelected = viewModel::onFacultySelected,
                        selectedDestinationIndex = viewModel.selectedFaculty.collectAsState().value,
                    )
                },
                departmentDestinations = if (departmentsDestinations.isNotEmpty()) departmentsList else null,
                sheetHandler = bottomSheetHandler,
                content = {
                    HomeContent()
                }
            )

        },
        onNonCompact = {
            TwoPaneLayout(
                pane2AnimationState =viewModel.selectedFaculty.collectAsState().value,
                showPane2 = true,
                pane1 = {
                    FacultiesDestinations(
                        modifier = Modifier,
                        destinations = facultiesDestinations,
                        onDestinationSelected = viewModel::onFacultySelected,
                        selectedDestinationIndex = viewModel.selectedFaculty.collectAsState().value,
                    )
                },
                pane2 = {
                    if (departmentsDestinations.isNotEmpty()) {
                        DepartmentsDestination(
                            modifier = Modifier,
                            enableBackNavigation = true,
                            title = "Departments List",
                            destinations = departmentsDestinations,
                            onDestinationSelected = viewModel::onDepartmentSelected,
                            selectedDestinationIndex = viewModel.selectedDepartment.collectAsState().value,
                            onDismissRequest = viewModel::onClearDepartmentSelection
                        )
                    } else {
                        HomeContent()
                    }
                }
            )
        }
    )


}

@Composable
private fun FacultiesDestinations(
    modifier: Modifier,
    destinations: List<NavigationItemInfo2<String>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
) {
    VerticalListNavigation(
        modifier = modifier,
        destinations = destinations,
        onDestinationSelected = onDestinationSelected,
        selectedDestinationIndex = selectedDestinationIndex
    )
}

@Composable
private fun DepartmentsDestination(
    modifier: Modifier = Modifier,
    title: String? = null,
    enableBackNavigation: Boolean = true,
    onDismissRequest: () -> Unit = {},
    destinations: List<NavigationItemInfo2<String>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
) {

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(Modifier, verticalAlignment = Alignment.CenterVertically) {
            AnimatedVisibility(enableBackNavigation) {
                IconButton(
                    onClick = onDismissRequest
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )

                }
            }
            if (title != null) {
                Box(Modifier) {
                    Text(text = title)
                }
            }

        }
        VerticalListNavigation(
            modifier = modifier,
            destinations = destinations,
            onDestinationSelected = onDestinationSelected,
            selectedDestinationIndex = selectedDestinationIndex,
            colors = NavigationItemProps(
                focusedColor = MaterialTheme.colorScheme.secondary,
                unFocusedColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        )

    }

}

@Composable
private fun VerticalListNavigation(
    modifier: Modifier = Modifier,
    destinations: List<NavigationItemInfo2<String>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
    colors: NavigationItemProps = NavigationItemProps(
        focusedColor = MaterialTheme.colorScheme.errorContainer,
        unFocusedColor = MaterialTheme.colorScheme.primaryContainer,
    )
) {
    Column(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        destinations.forEachIndexed { index, _ ->
            NavigationItem(
                modifier = Modifier.fillMaxWidth(),
                navigationItem = destinations[index],
                visibilityDelay = (index + 1) * 10L,
                selected = selectedDestinationIndex == index,
                onClick = {
                    onDestinationSelected(index)
                },
                onFocusing = {

                },
                colors = colors
            )
        }

    }

}

