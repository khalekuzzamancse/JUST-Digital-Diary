package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.office_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.event.AdminOfficesEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.office_list.state.OfficeListState
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemProps


/**
 * * It Show the have the List of in Bottom sheet.
 *  * In Compact Window Faculties will be shown in the bottom sheet,in NonCompact Window Faculties will be shown in SIDE_SHEET
 * @param modifier [Modifier]
 * @param destinations list of [NavigationItemInfo2] to represent the faculties
 * @param onDestinationSelected called when a faculty is selected
 * @param selectedDestinationIndex the destination that is selected.it is used to highlight the selected faculty as [NavigationItemInfo2]
 */

@Composable
internal fun OfficeList(
    modifier: Modifier=Modifier,
    state: OfficeListState,
    onEvent: (AdminOfficesEvent) -> Unit,
) {
        VerticalListNavigation(
            modifier = modifier.fillMaxWidth(),
            destinations = state.offices.map { faculty ->
                NavigationItemInfo2(
                    label = faculty.name,
                    iconText = faculty.numberOfSubOffices,
                    key = faculty.id
                )
            },
            onDestinationSelected = {index->
                onEvent(AdminOfficesEvent.AdminOfficesSelected(index))
            },
            selectedDestinationIndex = state.selected
        )

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
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
        ,
        verticalArrangement = Arrangement.spacedBy(8.dp),
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

