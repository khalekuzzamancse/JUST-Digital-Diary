package com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list

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
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.event.SubOfficeListEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.component.sub_office_list.state.SubOfficeListState
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.destination.event.SubOfficeDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemProps

@Composable
internal fun SubOfficeList(
    modifier: Modifier = Modifier,
    state: SubOfficeListState,
    onEvent: (SubOfficeDestinationEvent) -> Unit,
) {
    SubOfficeList(
        modifier = modifier,
        title = state.title,
        enableBackNavigation = state.enableBackNavigation,
        onDismissRequest = {
            onEvent(SubOfficeListEvent.DismissRequest)
        },
        destinations = state.subOffices.map {
            NavigationItemInfo2(
                key = it.id,
                label = it.name,
                iconText = it.employeeCnt
            )
        },
        onDestinationSelected = {index->
            onEvent(SubOfficeListEvent.SubOfficeSelected(index))
        },
        selectedDestinationIndex = state.selected
    )

}

@Composable
private fun SubOfficeList(
    modifier: Modifier = Modifier,
    title: String? = null,
    enableBackNavigation: Boolean = true,
    onDismissRequest: () -> Unit = {},
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

