package faculty.ui.department.departmentlist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.newui.CardInfoState
import common.newui.GenericInfoCard

@Composable
fun DepartmentCard(
    modifier: Modifier = Modifier,
    deptName: String,
    deptShortName: String,
    numOfTeacher: String,
    isSelected: Boolean,
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
        onSelect = onSelect
    )
}
