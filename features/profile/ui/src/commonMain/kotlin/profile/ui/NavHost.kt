@file:Suppress("functionName")

package profile.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import profile.presentationlogic.ProfileEvent

@Composable
fun ProfileNavHost(
    token: String?,
    onEvent: (ProfileEvent) -> Unit={},
    navigationIcon: (@Composable () -> Unit)? = null,
) {

    _TopBarDecorator(
        navigationIcon = navigationIcon
    ) {
        ProfileRoute(
            token = token,
            onEvent =onEvent
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun _TopBarDecorator(
    navigationIcon: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    if (navigationIcon != null)
                        navigationIcon()
                },
            )
        }
    ) {
        Box(
            modifier = Modifier.padding(it).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }

}
