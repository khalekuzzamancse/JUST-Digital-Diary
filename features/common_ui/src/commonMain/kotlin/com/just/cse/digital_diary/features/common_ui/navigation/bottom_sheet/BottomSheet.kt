package com.just.cse.digital_diary.features.common_ui.navigation.bottom_sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.just.cse.digital_diary.features.common_ui.navigation.NavigationItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun <T> BottomSheetItemDecorator(
    navigationItem: NavigationItem<T>,
    isSelected: Boolean,
    visibilityDelay: Long,
    onClick: () -> Unit,
) {
    var visible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(visibilityDelay)
            visible = true
            break
        }
    }
    AnimatedVisibility(
        visible=visible
    ){
        Row (
            modifier =
            Modifier
                .padding(top = 8.dp, bottom = 8.dp).
                wrapContentWidth().clickable {
                 //   selected = isSelected,
                     onClick()
                },

        ){
            Icon(
                navigationItem.unFocusedIcon,
                contentDescription = null
            )
            Text(
                text = navigationItem.label,
                )
        }
    }


}


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun <T> BottomSheetNavigation(
    destinations: List<NavigationItem<T>>,
    destinationDecorator: @Composable (index: Int) -> Unit,
) {
            FlowRow(modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
                destinations.forEachIndexed {index,_->
                    destinationDecorator(index)
                }
            }




}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onShitHide: () -> Unit = {},
    sheetState: SheetState,
    content: @Composable ColumnScope.() -> Unit,
) {
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(true) }
    AnimatedVisibility(visible = showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
                onShitHide()
            },
            sheetState = sheetState,
            content = content
        )
    }


}

