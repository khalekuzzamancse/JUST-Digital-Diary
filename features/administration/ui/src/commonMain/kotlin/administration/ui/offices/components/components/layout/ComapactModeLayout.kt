package administration.ui.offices.components.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import administration.ui.offices.components.components.office_list.OfficeList
import administration.ui.offices.components.components.office_list.state.OfficeListState
import administration.ui.offices.components.event.AdminOfficesEvent


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

