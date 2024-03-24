package faculty.ui.faculty.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_offices.event.FacultyListEvent
import faculty.ui.faculty.components.faculty_list.FacultyList
import faculty.ui.faculty.components.faculty_list.FacultyListState

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