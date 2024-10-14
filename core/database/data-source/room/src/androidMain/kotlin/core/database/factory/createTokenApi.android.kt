package core.database.factory

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import core.database.apis.TokenApi
import core.database.getDatabase
@Composable
actual fun createTokenApi(): TokenApi {
    return TokenApiImpl(getDatabase(LocalContext.current).tokenDao())
}

