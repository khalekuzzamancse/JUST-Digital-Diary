package common.ui.navigation.modal_drawer.drawer_decorator

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ui.navigation.modal_drawer.ModalDrawer
import common.ui.navigation.modal_drawer.sheet.event.DrawerSheetEvent
import common.ui.navigation.modal_drawer.sheet.state.ModalDrawerSheetState
import common.ui.navigation.modal_drawer.sheet.ui.DrawerSheet


/**
 * Non-default parameters: [state],[content]
 *  * has a distinct composable for drawer items without animation.
 *  * Handling both animated and non-animated scenarios within the same composable may inadvertently invoke the Animation API unnecessarily, leading to unwanted effects as the animation API executes on every frame.
 *  * It is crucial to exercise caution when dealing with animation APIs to avoid unintended calls or accidental object creation within the animation API, preventing unnecessary object creation.
 * */
@Composable
fun ModalDrawerDecorator(
    state: ModelDrawerState,
    onEvent:(DrawerSheetEvent)->Unit,
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    if (state.hasAnimation) {
        AnimationWithDrawer(
            state = state.sheetState,
            drawerState = state.drawerState,
            header = header,
            content = content,
            onEvent = onEvent
        )
    } else {
        AnimationLessDrawer(
            sheetState = state.sheetState,
            drawerState = state.drawerState,
            header = header,
            content = content,
            onEvent = onEvent
        )
    }
}

@Composable
private fun AnimationWithDrawer(
    modifier: Modifier = Modifier,
    state: ModalDrawerSheetState,
    drawerState: DrawerState,
    onEvent:(DrawerSheetEvent)->Unit,
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    ModalDrawer(
        modifier = modifier,
        drawerState =drawerState,
        sheet = {
            AnimatedVisibility(
                visible = drawerState .currentValue== DrawerValue.Open,
            ) {
                DrawerSheet(
                    state = state,
                    header = header,
                    onEvent = onEvent
                )
            }

        },
        content = content
    )

}


@Composable
private fun AnimationLessDrawer(
    modifier: Modifier = Modifier,
    sheetState: ModalDrawerSheetState,
    drawerState: DrawerState,
    onEvent:(DrawerSheetEvent)->Unit,
    header: @Composable () -> Unit = {},
    content: @Composable () -> Unit,
) {
    ModalDrawer(
        modifier = modifier,
        drawerState = drawerState,
        sheet = {
            DrawerSheet(
                state = sheetState,
                header = header,
                onEvent = onEvent
            )
        },
        content = content
    )

}

