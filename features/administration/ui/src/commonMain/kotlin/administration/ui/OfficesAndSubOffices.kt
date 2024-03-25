package administration.ui

import admin_office.domain.offices.repoisitory.AdminOfficeListRepository
import admin_office.domain.sub_offices.repoisitory.AdminSubOfficeListRepository
import administration.ui.offices.officelist.components.AdminOfficeList
import administration.ui.offices.officelist.components.AdminOfficesEvent
import administration.ui.offices.officelist.route.AdminOfficeListViewModel
import administration.ui.suboffice.subofficelist.AdminSubOfficeList
import administration.ui.suboffice.subofficelist.SubOfficesEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.layout.TwoPaneLayout
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
    val officesState = viewModel.state.collectAsState().value.officeState
    val subOfficeState = viewModel.state.collectAsState().value.subOfficeState
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
        if (subOfficeState != null)
        { viewModel.dismissSubOfficesDestination()
            true
        }
        else
            false
    }
    TwoPaneLayout(
        modifier = Modifier,
        navigationIcon = if (showSubOfficeDestination) Icons.AutoMirrored.Filled.ArrowBack else Icons.Default.Menu,
        onNavigationIconClick = if (showSubOfficeDestination) viewModel::dismissSubOfficesDestination else onExitRequest,
        showProgressBar = viewModel.state.collectAsState().value.isLoading,
        leftPane = {
            AdminOfficeList(
                officeListState = officesState,
                onEvent = officeEvent
            )
        },
        topOrRightPane = {
            if (subOfficeState != null) {
                AdminSubOfficeList(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                    ,
                    state = subOfficeState,
                    onEvent = subOfficeEvent
                )
            }
        },
        alignment = Alignment.TopStart,
        showTopOrRightPane = subOfficeState != null
    )


}
