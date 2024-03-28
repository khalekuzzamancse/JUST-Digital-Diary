package administration.ui.suboffice.subofficelist

import administration.ui.common.VerticalListNavigation
import administration.ui.offices.officelist.components.AdminOfficesEvent
import administration.ui.offices.officelist.components.OfficeCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.newui.generateAcronym
import common.ui.custom_navigation_item.NavigationItemInfo2
import common.ui.custom_navigation_item.NavigationItemProps

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