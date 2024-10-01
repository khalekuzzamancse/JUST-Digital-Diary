package administration.ui.public_

import administration.controller_presenter.factory.UiFactory
import administration.ui.common.SnackNProgressBarDecorator
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun AdminOfficeAndSubOfficeRoute(
    token: String?,
    onEmployeeListRequest: (String) -> Unit,
    navigationIcon: (@Composable () -> Unit)? = null,
    onBackButtonPress: @Composable (onBackButtonPress: () -> Boolean) -> Unit = {},
) {
    val viewModel = remember {
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
        _Layout(
            showSubOffices = viewModel.showSubOffice.collectAsState().value,
            offices = {
                AdminOfficeList(
                    controller = viewModel.officeController,
                )
            },
            subOffices = {
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

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
private fun _Layout(
    modifier: Modifier = Modifier,
    showSubOffices: Boolean,
    offices: @Composable () -> Unit,
    subOffices: @Composable () -> Unit
) {


    val windowSize = calculateWindowSizeClass().widthSizeClass
    val compact = WindowWidthSizeClass.Compact
    val medium = WindowWidthSizeClass.Medium
    val expanded = WindowWidthSizeClass.Expanded


    AnimatedContent(windowSize) { window ->
        when (window) {
            compact, medium -> {
                if (showSubOffices) {
                    subOffices()
                } else {
                    AnimatedVisibility(
                        modifier = modifier.fillMaxWidth(),
                        enter = fadeIn() + expandIn(),
                        exit = shrinkOut() + fadeOut(), //TODO: fix the animation transition later
                        visible = true
                    ) {
                        offices()
                    }
                }


            }


            expanded -> {
                Row(modifier = modifier.fillMaxWidth()) {
                    Box(Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                        offices()
                    }
                    if (showSubOffices) {
                        Spacer(Modifier.width(12.dp))
                        Box(Modifier.weight(1f), contentAlignment = Alignment.TopCenter) {
                            subOffices()
                        }

                    }

                }
            }
        }
    }


}
