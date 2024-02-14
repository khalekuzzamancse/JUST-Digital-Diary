package com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.NavItem
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.event.DrawerSheetEvent
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.item.DrawerItemDecorator
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.state.ModalDrawerSheetState
import com.just.cse.digital_diary.two_zero_two_three.common_ui.navigation.modal_drawer.sheet.state.NavGroup

/**
  * * Non Default params [state],[onEvent]
 */
@Composable
fun DrawerSheet(
    onEvent:(DrawerSheetEvent)->Unit,
    header: @Composable () -> Unit={},
    state: ModalDrawerSheetState,
) {
    DrawerSheet(
        header = header,
        groups = state.groups
    ) { item, serialNo ->
        DrawerItemDecorator(
            item = item,
            isSelected = item.id == state.selected.toString(),
            visibilityDelay = state.itemVisibilityDelay,
            onClick = {
                onEvent(DrawerSheetEvent.Selected(item.id))
            }
        )
    }
}

@Composable
private fun DrawerSheet(
    header: @Composable (() -> Unit)? = null,
    groups: List<NavGroup>,
    itemDecorator: @Composable (NavItem, serialNo: Long) -> Unit,
) {
    val lastIndex=groups.size-1
    ModalDrawerSheet(
        modifier = Modifier.width(IntrinsicSize.Max),
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()
            ),

        ) {
            if (header != null) {
                header()
            }
            groups.forEachIndexed { groupNo, group ->
                group.items.forEach { item ->
                    itemDecorator(item,0L)
                }
                if (groupNo!=lastIndex){
                    HorizontalDivider()
                }

            }
        }

    }

}