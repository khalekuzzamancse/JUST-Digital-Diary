package miscellaneous.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import common.ui.TopBarDecoratorCommon

/**
    In case of NavRail we do not want to show navigation icon that is why
    [enableNavigation] parameter is used
 */

@Composable
internal fun TopBarDecorator(
    enableNavigation: Boolean,
    onExitRequest: () -> Unit = {},
    title: String,
    screen: @Composable () -> Unit,
) {
    TopBarDecoratorCommon(
        topBarTitle = title,
        onNavigationIconClick = onExitRequest,
        topNavigationIcon = if (enableNavigation) Icons.Default.Menu else null
    ) {
        Box(Modifier.padding(it)) {
            screen()
        }
    }

}