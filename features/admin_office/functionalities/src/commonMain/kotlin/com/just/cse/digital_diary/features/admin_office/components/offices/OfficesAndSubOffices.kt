package com.just.cse.digital_diary.features.admin_office.components.offices

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.admin_office.components.destination.AdminOfficeListViewModel
import com.just.cse.digital_diary.features.admin_office.components.sub_offices.AdminSubOfficeList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.event.SubOfficesEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.repoisitory.AdminOfficeListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.repoisitory.AdminSubOfficeListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.components.AdminOfficeList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.event.AdminOfficesEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import kotlinx.coroutines.launch
/**
 * @param backHandler is to override the back button press functionality
 * used as composable so that composable and non composable both can be passed
 */
@Composable
fun OfficesAndSubOffices(
    onEmployeeListRequest: (String) -> Unit,
    officeRepository: AdminOfficeListRepository,
    subOfficeRepository: AdminSubOfficeListRepository,
    backHandler:@Composable (onBackButtonPress: () -> Unit)->Unit,
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
    val officesState = viewModel.state.collectAsState().value.officeState
    val subOfficeState = viewModel.state.collectAsState().value.subOfficeState

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

    WindowSizeDecorator(
        showProgressBar = viewModel.state.collectAsState().value.isLoading,
        onCompact = {
            if (subOfficeState != null) {
                AdminSubOfficeList(
                    state = subOfficeState,
                    onEvent = subOfficeEvent
                )
                backHandler{
                    viewModel.dismissSubOfficesDestination()
                }
            } else {
                AdminOfficeList(
                    officeListState = officesState,
                    onEvent = officeEvent
                )
            }
        },
        onNonCompact = {
            if (subOfficeState != null) {
                Row(Modifier.fillMaxWidth()) {
                    AdminOfficeList(
                        modifier = Modifier.weight(1f),
                        officeListState = officesState,
                        onEvent = officeEvent
                    )
                    AdminSubOfficeList(
                        modifier = Modifier.weight(1f),
                        state = subOfficeState,
                        onEvent = subOfficeEvent
                    )
                }
                backHandler{
                    viewModel.dismissSubOfficesDestination()
                }
            } else {
                AdminOfficeList(
                    officeListState = officesState,
                    onEvent = officeEvent
                )
            }
        }
    )


}
