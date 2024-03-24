package administration.ui.offices.components.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import administration.ui.offices.components.components.layout.CompactModeLayout
import administration.ui.offices.components.event.AdminOfficesEvent
import administration.ui.offices.components.components.office_list.state.OfficeListState

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