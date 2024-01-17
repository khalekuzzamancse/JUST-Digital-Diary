package com.just.cse.digital_diary.two_zero_two_three.department.local_destinations

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.AnimatedNavigationItemTextIcon
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemColor
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digital_diary.two_zero_two_three.department.common.EmployeeListGrid
import com.just.cse.digitaldiary.twozerotwothree.data.repository.department_info.DepartmentInfoRepository
import com.just.cse.digitaldiary.twozerotwothree.data.repository.repository.Employee


@Composable
internal fun NonCompactScreenLayout(
    employees: List<Employee>? = null,
    onEmployeeListRequest: (Int) -> Unit,
    onEmployeeListDismissRequest: () -> Unit,
    homeContent: (@Composable () -> Unit)? = null,
) {
    val destinations = listOf(
        NavigationItemInfo2(
            key = "1",
            label = "Employee",
            iconText = "EMP"
        ),
        NavigationItemInfo2(
            key = "1",
            label = "Staff",
            iconText = "STF"
        )
    )
    Row(modifier = Modifier.fillMaxWidth()) {
        EmployeeTypeDestinations(
            modifier = Modifier,
            destinations = destinations,
            onDestinationSelected = {
                onEmployeeListRequest(it)
            },
            selectedDestinationIndex = 0,
        )
        Spacer(Modifier.width(16.dp))
        Box(Modifier.weight(1f)) {
            //do not recompose the content so that it not again animated when back from department list
            if (homeContent != null) {
                homeContent()
            }
            if (employees != null) {
                AnimatedContent(
                    modifier = Modifier.fillMaxSize()
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                    targetState = true
                ) {
                    if (it) {
                        EmployeeList(
                            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.errorContainer),
                            onDismissRequest = onEmployeeListDismissRequest,
                            employees = employees
                        )
                    }

                }
            }
        }


    }
}

@Composable
private fun EmployeeList(
    modifier: Modifier = Modifier,
    employees: List<Employee>,
    title: String? = null,
    enableBackNavigation: Boolean = true,
    onDismissRequest: () -> Unit = {},
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
        EmployeeListGrid(
            modifier=Modifier.wrapContentWidth().align(Alignment.CenterHorizontally),
            employees =employees
        )


    }

}


@Composable
private fun EmployeeTypeDestinations(
    modifier: Modifier = Modifier,
    destinations: List<NavigationItemInfo2<String>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
) {

    VerticalListNavigation(
        modifier = modifier,
        destinations = destinations,
        onDestinationSelected = onDestinationSelected,
        selectedDestinationIndex = selectedDestinationIndex,
        colors = NavigationItemColor(
            focusedColor = MaterialTheme.colorScheme.secondary,
            unFocusedColor = MaterialTheme.colorScheme.secondaryContainer,
        )
    )


}

@Composable
private fun VerticalListNavigation(
    modifier: Modifier = Modifier,
    destinations: List<NavigationItemInfo2<String>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
    colors: NavigationItemColor = NavigationItemColor(
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
            AnimatedNavigationItemTextIcon(
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

