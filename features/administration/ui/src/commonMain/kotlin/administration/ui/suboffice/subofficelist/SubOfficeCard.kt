package administration.ui.suboffice.subofficelist

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.newui.CardInfoState
import common.newui.GenericInfoCard

@Composable
fun SubOfficeCard(
    modifier: Modifier = Modifier,
    name: String,
    shortName: String,
    employeeCount: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {

    val backgroundColorSelected = MaterialTheme.colorScheme.secondary
    val backgroundColorUnselected = MaterialTheme.colorScheme.surfaceContainer
    val iconSelected = Icons.Filled.Person
    val iconUnselected = Icons.Outlined.Person
    val countLabel = "Employee(s)"
    val state = CardInfoState(
        name = name,
        shortName = shortName,
        count = employeeCount,
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
