package administration.ui.public_

import administration.controller_presenter.controller.SubOfficeController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import common.newui.CardInfoState
import common.newui.GenericInfoCard
import common.newui.generateAcronym

@Composable
internal fun AdminSubOfficeList(
    modifier: Modifier=Modifier,
    controller:SubOfficeController,
    onEmployeeListRequest:(subOfficeId:String)->Unit,
) {
    val subOffices=controller.sobOffices.collectAsState().value
    val selected=controller.selected.collectAsState().value

    Column(modifier.verticalScroll(rememberScrollState())) {
       subOffices.forEachIndexed {index,subOffice->
            SubOfficeCard(
                name = subOffice.name,
                shortName = generateAcronym(subOffice.name),
                employeeCount = subOffice.employeeCnt,
                isSelected = selected==index,
                onSelect = {
                    controller.onSelected(index)
                    onEmployeeListRequest(subOffice.id)
                }
            )
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
