package administration.ui.public_

import administration.controller_presenter.factory.UiFactory
import administration.ui.common.SnackNProgressBarDecorator
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import common.ui.TwoPaneLayout

@Composable
internal fun AdminOfficeAndSubOfficeRoute(
    token: String?,
    onEmployeeListRequest: (String) -> Unit,
    navigationIcon: (@Composable () -> Unit)? = null,
    onBackButtonPress: @Composable (onBackButtonPress: () -> Boolean) -> Unit = {},
) {
    val viewModel = viewModel {
        OfficeScreenViewModel(
            officeController = UiFactory.createOfficerController(token),
            subOfficeController = UiFactory.createSubOfficeController(token)
        )
    }
    val notShowSubOffices = !(viewModel.showSubOffice.collectAsState().value)
    SnackNProgressBarDecorator(
        isLoading = viewModel.isLoading.collectAsState(false).value,
        snackBarMessage = viewModel.screenMessage.collectAsState(null).value,
        navigationIcon = if (notShowSubOffices) navigationIcon else {
            {
                IconButton(onClick = viewModel::onSubOfficeClose) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "back")
                }
            }
        }
    ) {

        TwoPaneLayout(
            showSecondPane = viewModel.showSubOffice.collectAsState().value,
            leftPane = {
                AdminOfficeList(
                    controller = viewModel.officeController,
                )
            },
            rightOrTopPane = {
                AdminSubOfficeList(
                    modifier = Modifier
                        .fillMaxSize(),
                    controller = viewModel.subOfficeController,
                    onEmployeeListRequest = onEmployeeListRequest
                )
            }
        )
    }


}
