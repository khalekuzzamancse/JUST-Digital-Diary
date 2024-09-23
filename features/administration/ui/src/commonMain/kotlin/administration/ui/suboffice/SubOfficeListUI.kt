package administration.ui.suboffice

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.newui.generateAcronym

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
@Composable
 fun SubOfficeListDestination(
    modifier: Modifier = Modifier,
    state: SubOfficeListState,
    onEvent: (SubOfficesEvent) -> Unit,
) {
    Column(modifier.verticalScroll(rememberScrollState())) {
        state.subOffices.forEachIndexed {index,subOffice->
            SubOfficeCard(
                name = subOffice.name,
                shortName = generateAcronym(subOffice.name),
                employeeCount = subOffice.employeeCnt,
                isSelected = state.selected==index,
                onSelect = {
                    onEvent(SubOfficesEvent.SubOfficeSelected(index))
                }
            )
        }

    }

}