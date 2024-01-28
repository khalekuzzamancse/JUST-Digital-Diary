package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.event.DepartmentListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.component.department_list.state.DepartmentListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.departments.destination.event.DepartmentDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemProps

@Composable
internal fun DepartmentsList(
    modifier: Modifier = Modifier,
    state: DepartmentListState,
    onEvent: (DepartmentDestinationEvent) -> Unit,
) {
    DepartmentsList(
        modifier = modifier,
        title = state.title,
        enableBackNavigation = state.enableBackNavigation,
        onDismissRequest = {
            onEvent(DepartmentListEvent.DismissRequest)
        },
        destinations = state.departments.map {
            NavigationItemInfo2(
                key = it.id,
                label = it.name,
                iconText = it.shortName
            )
        },
        onDestinationSelected = {index->
            onEvent(DepartmentListEvent.DepartmentSelected(index))
        },
        selectedDestinationIndex = state.selected
    )

}

@Composable
private fun DepartmentsList(
    modifier: Modifier = Modifier,
    title: String? = null,
    enableBackNavigation: Boolean = true,
    onDismissRequest: () -> Unit,
    destinations: List<NavigationItemInfo2<String>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
) {

    Column(
        modifier = modifier.fillMaxWidth()
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

