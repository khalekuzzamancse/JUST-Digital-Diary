package academic.ui.other.faculty

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.newui.CardInfoState
import common.newui.GenericInfoCard
import common.newui.generateAcronym

@Composable
fun FacultyCard(
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
