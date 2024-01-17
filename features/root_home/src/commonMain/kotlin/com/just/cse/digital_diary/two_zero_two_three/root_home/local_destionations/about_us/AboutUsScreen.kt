package com.just.cse.digital_diary.two_zero_two_three.root_home.local_destionations.about_us

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.common_ui.WindowSizeDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.top_bar.SimpleTopBar

@Composable
fun AboutUsScreen(
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

                floatingActionButtonPosition = FabPosition.Center
            ) {

                Column(
                    modifier = Modifier
                        .padding(it)
                        .fillMaxSize()

                        .verticalScroll(rememberScrollState())
                ) {
                    AppName()
                    AppDescription()
                    FeaturesSection()
                    ContactSection()
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
                AppName()
                AppDescription()
                FeaturesSection()
                ContactSection()
            }

        },
    )


}