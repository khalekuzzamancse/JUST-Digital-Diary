package administration.ui.offices.officelist.components

import administration.ui.common.VerticalListNavigation
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ui.custom_navigation_item.NavigationItemInfo2
import common.ui.custom_navigation_item.NavigationItemProps

@Composable
fun AdminOfficeList(
    modifier: Modifier=Modifier,
    officeListState: OfficeListState,
    onEvent: (AdminOfficesEvent) -> Unit,
) {
    CompactModeLayout(
        modifier=modifier,
        officeListState = officeListState,
        onEvent =onEvent,
    )

}
@Composable
internal fun CompactModeLayout(
    modifier: Modifier=Modifier,
    officeListState: OfficeListState,
    onEvent: (AdminOfficesEvent) -> Unit,
) {
    OfficeList(
        modifier = modifier,
        onEvent = onEvent,
        state = officeListState
    )


}

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
            selectedDestinationIndex = state.selected,
            colors = NavigationItemProps(
                unFocusedColor = MaterialTheme.colorScheme.tertiaryContainer,
                focusedColor = MaterialTheme.colorScheme.secondary,
                iconTint = MaterialTheme.colorScheme.primary,
                iconLabelColor = MaterialTheme.colorScheme.onPrimary

            )
        )

}



