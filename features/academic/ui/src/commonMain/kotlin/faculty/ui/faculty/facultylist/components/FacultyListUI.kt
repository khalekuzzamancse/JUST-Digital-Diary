package faculty.ui.faculty.facultylist.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun FacultyListDestination(
    modifier: Modifier = Modifier,
    state: FacultyListState,
    onEvent: (FacultyListEvent) -> Unit,
) {
    FacultyList(
        modifier = modifier,
        state = state,
        onEvent = onEvent
    )
}


@Composable
fun FacultyList(
    modifier: Modifier = Modifier,
    state: FacultyListState,
    onEvent: (FacultyListEvent) -> Unit,
) {

    Column(modifier.verticalScroll(rememberScrollState())) {
        state.faculties.forEachIndexed {index,faculty->
            FacultyCard(
                facultyName = faculty.name,
                departmentCount = faculty.numberOfDepartment,
                isSelected = state.selected==index,
                onSelect = {
                    onEvent(FacultyListEvent.FacultySelected(index))
                }
            )
        }

    }
}

