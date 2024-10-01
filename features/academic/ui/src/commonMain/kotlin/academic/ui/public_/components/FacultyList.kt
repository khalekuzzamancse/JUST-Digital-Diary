package academic.ui.public_.components

import academic.controller_presenter.controller.FacultyController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import common.ui.CardInfoState
import common.ui.GenericInfoCard
import common.ui.generateAcronym



@Composable
internal fun Faculty(
    modifier: Modifier = Modifier,
    controller: FacultyController,
) {
    val faculties = controller.faculties.collectAsState().value
    val selected = controller.selected.collectAsState().value
    Column(modifier.verticalScroll(rememberScrollState())) {
        faculties.forEachIndexed { index, faculty ->
            _FacultyCard(
                facultyName = faculty.name,
                departmentCount = faculty.numberOfDepartment,
                isSelected = selected == index,
                onSelect = {
                    controller.onSelected(index)
                }
            )
        }

    }
}


@Composable
private fun _FacultyCard(
    modifier: Modifier = Modifier,
    facultyName: String,
    departmentCount: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {

    val backgroundColorSelected = MaterialTheme.colorScheme.primaryContainer
    val backgroundColorUnselected = MaterialTheme.colorScheme.tertiaryContainer
    val iconSelected = Icons.Filled.School
    val iconUnselected = Icons.Outlined.School
    val countLabel = "Departments"

    val state = CardInfoState(
        name = facultyName,
        shortName = generateAcronym(facultyName),
        count = departmentCount,
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
        onSelect = onSelect
    )
}
