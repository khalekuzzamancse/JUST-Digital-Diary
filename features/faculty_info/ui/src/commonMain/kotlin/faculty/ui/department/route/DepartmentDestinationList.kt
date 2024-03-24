package faculty.ui.department.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import faculty.ui.department.components.component.DepartmentListState
import faculty.ui.department.components.component.DepartmentsList
import faculty.ui.department.components.event.DepartmentListEvent

@Composable
internal fun DepartmentListDestination(
    modifier: Modifier = Modifier,
    state: DepartmentListState,
    onEvent: (DepartmentListEvent) -> Unit,
) {
    DepartmentsList(
        modifier = modifier,
        state = state,
        onEvent = onEvent
    )

}