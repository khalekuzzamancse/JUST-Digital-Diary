package navigation.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import common.ui.SimpleTopBar


@Composable
internal fun TopBarDecorator(
    title: String?=null,
    navigationIcon:ImageVector=Icons.Default.Menu,
    onExitRequest:()->Unit,
    content:@Composable ()->Unit,
) {
    Scaffold(
        topBar = {
            SimpleTopBar(
                title=title?:"",
                navigationIcon=navigationIcon,
                onNavigationIconClick=onExitRequest
            )
        }
    ) {
        Box(Modifier.padding(it)){
            content()
        }
    }

}