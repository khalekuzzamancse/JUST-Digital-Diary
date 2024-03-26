package administration.ui

import admin_office.domain.offices.repoisitory.AdminOfficeListRepository
import admin_office.domain.sub_offices.repoisitory.AdminSubOfficeListRepository
import administration.ui.offices.officelist.components.AdminOfficeList
import administration.ui.offices.officelist.components.AdminOfficesEvent
import administration.ui.offices.officelist.components.OfficeListState
import administration.ui.offices.officelist.route.AdminOfficeListViewModel
import administration.ui.suboffice.subofficelist.AdminSubOfficeList
import administration.ui.suboffice.subofficelist.SubOfficeListState
import administration.ui.suboffice.subofficelist.SubOfficesEvent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import common.newui.SnackNProgressBarDecorator
import common.newui.TwoPaneNewUIPros

import kotlinx.coroutines.launch

/**
 * @param backHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 */
@Composable
internal fun OfficesAndSubOffices(
    onEmployeeListRequest: (String) -> Unit,
    officeRepository: AdminOfficeListRepository,
    subOfficeRepository: AdminSubOfficeListRepository,
    onExitRequest: () -> Unit,
    backHandler: @Composable (onBackButtonPress: () -> Boolean) -> Unit,
) {

    val scope = rememberCoroutineScope()
    val viewModel = remember {
        AdminOfficeListViewModel(
            repository = officeRepository,
            subOfficeListRepository = subOfficeRepository
        )
    }
    LaunchedEffect(Unit) {
        viewModel.loadOffices()
    }
    val officesState: OfficeListState = viewModel.uiState.collectAsState().value.officeState
    val subOfficeState: SubOfficeListState? = viewModel.uiState.collectAsState().value.subOfficeState
    val showSubOfficeDestination = subOfficeState != null

    val officeEvent: (AdminOfficesEvent) -> Unit = { event ->
        when (event) {
            is AdminOfficesEvent.AdminOfficesSelected -> {
                scope.launch {
                    viewModel.onOfficeSelected(event.index)
                }
            }
        }

    }
    val subOfficeEvent: (SubOfficesEvent) -> Unit = { event ->
        when (event) {
            is SubOfficesEvent.SubOfficeSelected -> {
                val subOfficeId = viewModel.getSubOfficeId(event.index)
                if (subOfficeId != null)
                    onEmployeeListRequest(subOfficeId)
            }
        }

    }
    backHandler {
        if (subOfficeState != null) {
            viewModel.dismissSubOfficesDestination()
            true
        } else
            false
    }
    SnackNProgressBarDecorator(
        snackBarData = viewModel.uiState.collectAsState().value.snackBarData,
        showProgressBar = viewModel.uiState.collectAsState().value.isLoading,
        onSnackBarCloseRequest = viewModel::clearSnackBarMessages
    ) {
        _OfficeNSubOfficeListRaw(
            modifier = Modifier,
            officesState = officesState,
            subOfficeState = subOfficeState,
            onOfficeEvent = officeEvent,
            onSubOfficeEvent = subOfficeEvent,
            onSubOfficeDestinationCloseRequest = viewModel::dismissSubOfficesDestination,
            onExitRequest = onExitRequest
        )
    }


}

@Composable
private fun _OfficeNSubOfficeListRaw(
    modifier: Modifier = Modifier,
    officesState: OfficeListState,
    subOfficeState: SubOfficeListState?,
    onOfficeEvent: (AdminOfficesEvent) -> Unit,
    onSubOfficeEvent: (SubOfficesEvent) -> Unit,
    onSubOfficeDestinationCloseRequest: () -> Unit,
    onExitRequest: () -> Unit,
) {
    val showSubOfficeList = subOfficeState != null
    val navigationIcon =
        if (showSubOfficeList) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu
    val alignment = Alignment.TopStart
    val props = TwoPaneNewUIPros(
        showTopOrRightPane = showSubOfficeList,
        alignment = alignment,
        navigationIcon = navigationIcon
    )
    common.newui.TwoPaneLayout(
        modifier = modifier,
        props = props,
        onNavigationIconClick = if (showSubOfficeList) onSubOfficeDestinationCloseRequest else onExitRequest,
        leftPane = {
            AdminOfficeList(
                officeListState = officesState,
                onEvent = onOfficeEvent
            )
        },
        topOrRightPane = {
            if (subOfficeState != null) {
                AdminSubOfficeList(
                    modifier = Modifier
                        .fillMaxSize(),
                    state = subOfficeState,
                    onEvent = onSubOfficeEvent
                )
            }
        },

        )

}

