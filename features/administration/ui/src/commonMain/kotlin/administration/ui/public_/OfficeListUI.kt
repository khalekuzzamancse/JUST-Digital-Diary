package administration.ui.public_

import administration.controller_presenter.controller.OfficeController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import common.newui.EmptyContentScreen
import common.ui.CardInfoState
import common.ui.GenericInfoCard
import common.ui.generateAcronym

@Composable
internal fun AdminOfficeList(
    modifier: Modifier = Modifier,
    controller: OfficeController,
) {
    val offices = controller.offices.collectAsState().value
    val selected = controller.selected.collectAsState().value
    val isNotFetching =!(controller.isFetching.collectAsState().value)
    if (offices.isEmpty()&&isNotFetching) {
        EmptyContentScreen(message =  "No office found")
    } else {
        Column(modifier.verticalScroll(rememberScrollState())) {
            offices.forEachIndexed { index, faculty ->
                OfficeCard(
                    officeName = faculty.name,
                    subOfficeCount = faculty.numberOfSubOffices,
                    isSelected = selected == index,
                    onSelect = {
                        controller.onSelected(index)
                    }
                )
            }

        }
    }


}


@Composable
private fun OfficeCard(
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

