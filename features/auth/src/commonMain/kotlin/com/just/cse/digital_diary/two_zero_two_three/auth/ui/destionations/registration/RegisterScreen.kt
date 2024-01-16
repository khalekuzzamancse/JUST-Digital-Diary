package com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.registration

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.common.AuthTopBar
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.destionations.WindowSizeDecorator

@Composable
fun RegisterScreenCom(
    onExitRequest: () -> Unit,
    onRegisterSuccess: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    val viewModel = remember { RegistrationViewModel(scope) }
    WindowSizeDecorator(
        showProgressBar =  viewModel.showProcessBar.collectAsState().value,
        snackBarMessage = viewModel.screenMessage.collectAsState().value,
        onCompact = {
            Scaffold(
                topBar = {
                    AuthTopBar(
                        title = "Registration Form",
                        onNavigationIconClick = onExitRequest
                    )
                }
            ) {
                RegisterSection(
                    modifier = Modifier.padding(it).verticalScroll(rememberScrollState()),
                    viewModel = viewModel,
                    onExitRequest = onExitRequest
                )

            }

        },
        onNonCompact = {
            RegisterSection(
                allowFooterBackNavigation = true,
                modifier = Modifier.verticalScroll(rememberScrollState()),
                viewModel = viewModel,
                onExitRequest = onExitRequest

            )
        }

    )



}