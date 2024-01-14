package com.just.cse.digital_diary.features.common_ui.navigation.bottom_sheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetDecorator(
    sheetState: SheetState,
    topBar: @Composable (() -> Unit)? = null,
    sheetContent: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    BottomSheetScaffold(
        sheetShadowElevation = 8.dp,
        topBar = topBar,
        scaffoldState = scaffoldState,
        sheetContent = {
                sheetContent()

        },
        content = {
            Box(Modifier.padding(it)) {
                content()
            }
        }
    )
}