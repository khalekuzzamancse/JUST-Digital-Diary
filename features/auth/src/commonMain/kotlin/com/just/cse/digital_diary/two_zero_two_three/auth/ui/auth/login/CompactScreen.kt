package com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.login

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ModalDrawerGroup
import com.just.cse.digital_diary.two_zero_two_three.auth.ui.auth.modal_drawer.ScreenWithDrawer

@Composable
fun MediumScreen(
    modifier: Modifier,
    headerSection: @Composable ((Modifier) -> Unit),
    navigationSection: @Composable ((Modifier) -> Unit),
    loginSection: @Composable ((Modifier) -> Unit)
) {
    Row(modifier = modifier.padding(16.dp).fillMaxSize()) {
        navigationSection(Modifier.weight(1f))
        Column {
            headerSection(Modifier)
            loginSection(Modifier.wrapContentWidth())
        }
    }
}

@Composable
fun ExpandedScreen(
    modifier: Modifier,
    headerSection: @Composable ((Modifier) -> Unit),
    navigationSection: @Composable ((Modifier) -> Unit),
    loginSection: @Composable ((Modifier) -> Unit)
) {
    Row(modifier = modifier.padding(16.dp).fillMaxSize()) {
        navigationSection(Modifier.weight(1f))
        loginSection(Modifier.weight(1f).wrapContentWidth())
        headerSection(Modifier)
    }
}


@Composable
fun CompactScreen(
    modifier: Modifier,
    drawerState: DrawerState,
    onDrawerItemClick: (String) -> Unit,
    onDrawerCloseRequested: () -> Unit,
    destination: List<ModalDrawerGroup>,
    headerSection: @Composable ((Modifier) -> Unit),
    loginSection: @Composable ((Modifier) -> Unit)
) {

    BoxWithConstraints(modifier) {
        val width = this.maxWidth
        Row(modifier = Modifier.fillMaxSize()) {
            ScreenWithDrawer(
                drawerState = drawerState,
                drawerWidth = width * .8f,//80%
                destination = destination,
                closeDrawer = onDrawerCloseRequested,
                onDrawerItemClick = onDrawerItemClick,
            ) {
                Column(Modifier) {
                    headerSection(Modifier)
                    loginSection(Modifier)
                }

            }

        }
    }

}