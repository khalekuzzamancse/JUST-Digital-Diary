package administration.ui.offices

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.newui.CardInfoState
import common.newui.GenericInfoCard
import common.newui.generateAcronym

@Composable
fun OfficeCard(
    modifier: Modifier = Modifier,
    officeName: String,
    subOfficeCount: String,
    isSelected: Boolean,
    onSelect: () -> Unit
) {

    val backgroundColorSelected = MaterialTheme.colorScheme.primaryContainer
    val backgroundColorUnselected = MaterialTheme.colorScheme.tertiaryContainer
    val iconSelected = Icons.Filled.Apartment
    val iconUnselected = Icons.Outlined.Apartment
    val countLabel = "SubOffice"

    val state = CardInfoState(
        name = officeName,
        shortName = generateAcronym(officeName),
        count = subOfficeCount,
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
