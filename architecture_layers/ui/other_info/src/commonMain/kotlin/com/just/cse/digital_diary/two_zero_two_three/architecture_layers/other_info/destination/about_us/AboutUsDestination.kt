package com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.about_us

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.model.AboutUsResponseModel
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.domain.admin_officers.repoisitory.OtherInfoRepository
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.other_info.destination.about_us.state.AboutUsState
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar

@Composable
fun AboutUsDestination(
    repository: OtherInfoRepository,
) {
    var state by remember { mutableStateOf<AboutUsState?>(null) }
    LaunchedEffect(Unit) {
        val result = repository.getAboutUs()
        if (result is AboutUsResponseModel.Success){
            state=AboutUsState(
                appName = result.data.appName,
                developedDepartmentName = result.data.developedDepartmentName,
                universityName = result.data.universityName,
                otherInfo = result.data.otherInfo
            )
        }
    }
    state?.let {aboutUsState->
        AboutUsDestination(
            state=aboutUsState,
            onExitRequest = {}
        )
    }


}

@Composable
private fun AboutUsDestination(
    state: AboutUsState,
    onExitRequest: () -> Unit,
) {

    WindowSizeDecorator(
        onCompact = {
            Scaffold(
                topBar = {
                    SimpleTopBar(
                        onNavigationIconClick = onExitRequest,
                        title = "About Us",
                        navigationIcon = Icons.Default.Menu
                    )
                },
            ) {

                Column(
                    modifier = Modifier
                        .padding(it)
                        .padding(8.dp)
                        .fillMaxSize()

                        .verticalScroll(rememberScrollState())
                ) {
                    AppName(state.appName)
                    DeptAndUniversityName(
                        deptName = state.developedDepartmentName,
                        universityName = state.universityName
                    )
                }


            }
        },
        onNonCompact = {
            Column(
                modifier = Modifier
                    .padding(48.dp)
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                AppName(state.appName)
                DeptAndUniversityName(
                    deptName = state.developedDepartmentName,
                    universityName = state.universityName
                )
            }

        },
    )


}