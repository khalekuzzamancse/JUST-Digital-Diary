package administration.ui.suboffice.subofficelist

import administration.ui.common.VerticalListNavigation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
    SubOfficeListDestination(
        modifier = modifier,
        destinations = state.subOffices.map {
            NavigationItemInfo2(
                key = it.id,
                label = it.name,
                iconText = it.employeeCnt
            )
        },
        onDestinationSelected = {index->
            onEvent(SubOfficesEvent.SubOfficeSelected(index))
        },
        selectedDestinationIndex = state.selected
    )

}

@Composable
private fun SubOfficeListDestination(
    modifier: Modifier = Modifier,
    destinations: List<NavigationItemInfo2<String>>,
    onDestinationSelected: (Int) -> Unit,
    selectedDestinationIndex: Int,
) {

    Column(
        modifier = modifier.fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        VerticalListNavigation(
            modifier = Modifier,
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


