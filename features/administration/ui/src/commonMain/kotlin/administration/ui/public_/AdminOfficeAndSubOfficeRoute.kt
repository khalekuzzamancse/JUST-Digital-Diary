package administration.ui.public_

import administration.controller_presenter.controller.OfficeController
import administration.controller_presenter.controller.SubOfficeController
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.newui.TwoPaneNewUIPros

@Composable
internal fun AdminOfficeAndSubOfficeRoute(
    viewModel: OfficeScreenViewModel,
    token: String?,
    onEmployeeListRequest: (String) -> Unit,
    onExitRequest: () -> Unit={},
    onBackButtonPress:@Composable (onBackButtonPress: () -> Boolean)->Unit={},
) {
    val showSubOfficeList = viewModel.showSubOffice.collectAsState().value
    _OfficeNSubOfficeListRaw(
        modifier = Modifier,
        officeController = viewModel.officeController,
        subOfficeController = viewModel.subOfficeController,
        showSubOfficeList = showSubOfficeList,
        onSubOfficeDestinationCloseRequest = viewModel::onSubOfficeClose,
        onExitRequest = onExitRequest,
        onEmployeeListRequest = onEmployeeListRequest
    )
}


@Composable
private fun _OfficeNSubOfficeListRaw(
    modifier: Modifier = Modifier,
    officeController: OfficeController,
    subOfficeController: SubOfficeController,
    onEmployeeListRequest: (String) -> Unit,
    showSubOfficeList:Boolean,
    onSubOfficeDestinationCloseRequest: () -> Unit,
    onExitRequest: () -> Unit,
) {

    val navigationIcon = if (showSubOfficeList) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu
    val props = TwoPaneNewUIPros(
        showTopOrRightPane = showSubOfficeList,
        alignment =  Alignment.TopStart,
        navigationIcon = navigationIcon
    )
    common.newui.TwoPaneLayout(
        modifier = modifier,
        props = props,
        onNavigationIconClick = if (showSubOfficeList) onSubOfficeDestinationCloseRequest else onExitRequest,
        leftPane = {
            AdminOfficeList(
                controller = officeController,
            )
        },
        topOrRightPane = {
            if (showSubOfficeList) {
                AdminSubOfficeList(
                    modifier = Modifier
                        .fillMaxSize(),
                    controller = subOfficeController,
                    onEmployeeListRequest = onEmployeeListRequest
                )
            }
        },

        )

}