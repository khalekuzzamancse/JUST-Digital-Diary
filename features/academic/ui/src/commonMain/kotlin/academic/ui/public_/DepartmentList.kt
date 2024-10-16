package academic.ui.public_

import academic.presentationlogic.controller.public_.DepartmentController
import academic.ui.AcademicModuleEvent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.EmptyContentScreen
import common.ui.CardInfoState
import common.ui.GenericInfoCard

/**
 * @param onTeachersRequest This should not handle by controller it should be propagate
 * to parent so inform that it should navigate
 */
@Composable
internal fun Departments(
    modifier: Modifier = Modifier,
    controller: DepartmentController,
    onTeachersRequest: (String) -> Unit,
    isAdmin: Boolean = true,
    onEvent: (AcademicModuleEvent)->Unit,
) {
    val departments = controller.departments.collectAsState().value
    val selected = controller.selected.collectAsState().value
    val isNotFetching =!(controller.isLoading.collectAsState().value)
    if (departments.isEmpty()&&isNotFetching) {
        EmptyContentScreen(message = "No department found")
    } else {

        Column(
            modifier = modifier.fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            departments.forEachIndexed { index, dept ->
                _DepartmentCard(
                    modifier = Modifier,
                    deptName = dept.name,
                    deptShortName = dept.shortname,
                    numOfTeacher = dept.membersCount,
                    isSelected = selected == index,
                    onSelect = {
                        controller.onSelected(index)
                        onTeachersRequest(dept.id)
                    },
                    showEditButton = isAdmin,
                    showDeleteButton = isAdmin,
                    onEditRequest = {
                        onEvent(AcademicModuleEvent.UpdateDeptRequest(dept.id))
                    },
                    onDeleteRequest = {
                        onEvent(AcademicModuleEvent.DeleteDeptRequest(dept.id))
                    }
                )
            }
        }
    }

}

@Composable
private fun _DepartmentCard(
    modifier: Modifier = Modifier,
    deptName: String,
    deptShortName: String,
    numOfTeacher: String,
    isSelected: Boolean,
    showEditButton: Boolean,
    showDeleteButton: Boolean ,
    onEditRequest: () -> Unit,
    onDeleteRequest: () -> Unit,
    onSelect: () -> Unit
) {

    val backgroundColorSelected = MaterialTheme.colorScheme.secondary
    val backgroundColorUnselected = MaterialTheme.colorScheme.surfaceContainer
    val iconSelected = Icons.Filled.Person
    val iconUnselected = Icons.Outlined.Person
    val countLabel = "Teacher(s)"
    val state = CardInfoState(
        name = deptName,
        shortName = deptShortName,
        count = numOfTeacher,
        isSelected = isSelected,
        backgroundColorSelected = backgroundColorSelected,
        backgroundColorUnselected = backgroundColorUnselected,
        iconSelected = iconSelected,
        iconUnselected = iconUnselected,
        countLabel = countLabel
    )

    GenericInfoCard(
        modifier = modifier,
        state = state,
        onSelect = onSelect,
        showEditButton = showEditButton,
        showDeleteButton = showDeleteButton,
        onEditRequest = onEditRequest,
        onDeleteRequest = onDeleteRequest

    )
}
