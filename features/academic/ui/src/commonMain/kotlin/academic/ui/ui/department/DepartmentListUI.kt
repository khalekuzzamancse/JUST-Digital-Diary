package faculty.ui.department

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

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
@Composable
 fun DepartmentsList(
    modifier: Modifier = Modifier,
    state: DepartmentListState,
    onEvent: (DepartmentListEvent) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        state.departments.forEachIndexed{index, dept ->
            DepartmentCard(
                modifier=Modifier,
                deptName = dept.name,
                deptShortName = dept.shortName,
                numOfTeacher = Random.nextInt(10,30).toString(),
                isSelected = state.selected==index,
                onSelect = {
                    onEvent(DepartmentListEvent.DepartmentSelected(index))
                }
            )
        }

    }

}
