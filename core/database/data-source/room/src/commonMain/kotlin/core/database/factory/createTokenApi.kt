package core.database.factory

import androidx.compose.runtime.Composable
import core.database.apis.TokenApi
@Composable
expect fun createTokenApi(): TokenApi