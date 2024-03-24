package faculty.ui.department.components.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import faculty.ui.department.components.event.DepartmentListEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemInfo2
import com.just.cse.digital_diary.two_zero_two_three.common_ui.custom_navigation_item.NavigationItemProps

@Composable
 fun DepartmentsList(
    modifier: Modifier = Modifier,
    state: DepartmentListState,
    onEvent: (DepartmentListEvent) -> Unit,
) {
    DepartmentsList(
        modifier = modifier,
        destinations = state.departments.map {
            NavigationItemInfo2(
                key = it.id,
                label = it.name,
                iconText = it.shortName
            )
        },
        onDestinationSelected = {index->
            onEvent(DepartmentListEvent.DepartmentSelected(index))
        },
        selectedDestinationIndex = state.selected?:-1
    )

}

@Composable
private fun DepartmentsList(
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
            modifier = Modifier.fillMaxWidth(),
            destinations = destinations,
            onDestinationSelected = onDestinationSelected,
            selectedDestinationIndex = selectedDestinationIndex,
            colors = NavigationItemProps(
                focusedColor = MaterialTheme.colorScheme.secondary,
                unFocusedColor = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.small
            )
        )


    }
}

