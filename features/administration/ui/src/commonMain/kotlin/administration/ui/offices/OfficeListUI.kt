package administration.ui.offices

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ui.custom_navigation_item.NavigationItemInfo2

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
    Column(modifier.verticalScroll(rememberScrollState())) {
        state.offices.forEachIndexed {index,faculty->
            OfficeCard(
                officeName = faculty.name,
                subOfficeCount = faculty.numberOfSubOffices,
                isSelected = state.selected==index,
                onSelect = {
                    onEvent(AdminOfficesEvent.AdminOfficesSelected(index))
                }
            )
        }

    }


}



