package faculty.ui.faculty.facultylist.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ui.custom_navigation_item.NavigationItemInfo2
import faculty.ui.common.VerticalListNavigation

@Composable
internal fun FacultyListDestination(
    modifier: Modifier = Modifier,
    state: FacultyListState,
    onEvent: (FacultyListEvent) -> Unit,
){
    FacultyList(
        modifier = modifier,
        state = state,
        onEvent = onEvent
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
fun FacultyList(
    modifier: Modifier = Modifier,
    state: FacultyListState,
    onEvent: (FacultyListEvent) -> Unit,
) {
    val destinations = state.faculties.map { faculty ->
        NavigationItemInfo2(
            label = faculty.name,
            iconText = faculty.numberOfDepartment,
            key = faculty.id
        )
    }

        VerticalListNavigation(
            modifier = modifier.fillMaxWidth(),
            destinations = destinations,
            onDestinationSelected = { index ->
                onEvent(FacultyListEvent.FacultySelected(index))
            },
            selectedDestinationIndex = state.selected?:-1
        )

}

