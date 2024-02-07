package com.just.cse.digital_diary.features.admin_office.components.offices

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.features.admin_office.components.destination.AdminOfficeListViewModel
import com.just.cse.digital_diary.features.admin_office.components.sub_offices.AdminSubOfficeList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layer.ui.admin_sub_offices.event.SubOfficeDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_offices.repoisitory.AdminOfficeListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_sub_offices.repoisitory.AdminSubOfficeListRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.destination.destination.AdminOfficeList
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.ui.admin_offices.event.AdminOfficesDestinationEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
import kotlinx.coroutines.launch

@Composable
fun AdminOffices(
    onEmployeeListRequest: (String) -> Unit,
    officeRepository: AdminOfficeListRepository,
    subOfficeRepository: AdminSubOfficeListRepository,
    onExitRequest: () -> Unit,
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
    val officeEvent: (AdminOfficesDestinationEvent) -> Unit = { event ->
        when (event) {
            is AdminOfficesDestinationEvent.AdminOfficesListEvent.AdminOfficesSelected -> {
                scope.launch {
                    viewModel.onOfficeSelected(event.index)
                }
            }

            is AdminOfficesDestinationEvent.ExitRequest -> {
                onExitRequest()
            }
        }

    }
    val subOfficeEvent: (SubOfficeDestinationEvent) -> Unit = { event ->
        when (event) {
            SubOfficeDestinationEvent.ExitRequest -> {
                viewModel.dismissSubOfficesDestination()
            }

            is SubOfficeDestinationEvent.SubOfficeListEvent.SubOfficeSelected -> {
                val subOfficeId = viewModel.getSubOfficeId(event.index)
                if (subOfficeId != null)
                    onEmployeeListRequest(subOfficeId)
            }
        }

    }

    WindowSizeDecorator(
        showProgressBar = viewModel.state.collectAsState().value.isLoading,
        onCompact = {
            Box(Modifier.fillMaxSize()) {
                AdminOfficeList(
                    officeListState = officesState,
                    onEvent = officeEvent
                )
                if (viewModel.state.collectAsState().value.showSubOfficeList) {
                    subOfficeState?.let {
                        Box(
                            Modifier.matchParentSize()
                                .background(MaterialTheme.colorScheme.primaryContainer)
                        ) {
                            AdminSubOfficeList(
                                state = subOfficeState,
                                onEvent = subOfficeEvent
                            )
                        }

                    }
                }

            }
        },
        onNonCompact = {
            TwoPaneLayout(
                secondaryPaneAnimationState = false,
                showTopOrRightPane = true,
                leftPane = {
                    AdminOfficeList(
                        officeListState = officesState,
                        onEvent = officeEvent
                    )
                },
                topOrRightPane = {
                    if (viewModel.state.collectAsState().value.showSubOfficeList) {
                        subOfficeState?.let {
                            AdminSubOfficeList(
                                state = subOfficeState,
                                onEvent = subOfficeEvent
                            )


                        }
                    }
                }
            )
        }
    )


}