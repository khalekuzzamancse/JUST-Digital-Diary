package administration.ui.public_

import administration.controller_presenter.controller.SubOfficeController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.EmptyContentScreen
import common.ui.CardInfoState
import common.ui.GenericInfoCard
import common.ui.generateAcronym

@Composable
internal fun AdminSubOfficeList(
    modifier: Modifier = Modifier,
    controller: SubOfficeController,
    onEmployeeListRequest: (subOfficeId: String) -> Unit,
) {
    val subOffices = controller.sobOffices.collectAsState().value
    val selected = controller.selected.collectAsState().value
    val isNotFetching =!(controller.isFetching.collectAsState().value)
    if (subOffices.isEmpty()&&isNotFetching) {
        EmptyContentScreen(message = "No sub office found")
    } else {
        Column(modifier.verticalScroll(rememberScrollState())) {
            subOffices.forEachIndexed { index, subOffice ->
                SubOfficeCard(
                    name = subOffice.name,
                    shortName = generateAcronym(subOffice.name),
                    employeeCount = subOffice.employeeCnt,
                    isSelected = selected == index,
                    onSelect = {
                        controller.onSelected(index)
                        onEmployeeListRequest(subOffice.id)
                    }
                )
            }

        }
    }


}

@Composable
private fun SubOfficeCard(
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
    val state = remember {
        CardInfoState(
            name = name,
            nameFontSize = 16,
            shape = RoundedCornerShape(12.dp),
            shortName = shortName,
            count = employeeCount,
            isSelected = isSelected,
            backgroundColorSelected = backgroundColorSelected,
            backgroundColorUnselected = backgroundColorUnselected,
            iconSelected = iconSelected,
            iconUnselected = iconUnselected,
            countLabel = countLabel
        )

    }
    GenericInfoCard(
        modifier = modifier,
        state = state,
        onSelect = onSelect
    )
}
