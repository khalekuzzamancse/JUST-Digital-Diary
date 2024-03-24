package administration.ui.sub_offices.route

import administration.ui.sub_offices.component.component.sub_office_list.SubOfficeListDestination
import administration.ui.sub_offices.component.component.sub_office_list.state.SubOfficeListState
import administration.ui.sub_offices.component.event.SubOfficesEvent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AdminSubOfficeList(
    modifier: Modifier=Modifier,
    state: SubOfficeListState,
    onEvent: (SubOfficesEvent)->Unit,
) {

    SubOfficeListDestination(
        modifier=modifier,
        state = state,
        onEvent = onEvent
    )

}