package core.database.factory

import androidx.compose.runtime.Composable
import core.database.apis.TokenApi
import core.database.getDatabase
@Composable
actual fun createTokenApi(): TokenApi {
   return TokenApiImpl(getDatabase().tokenDao())
}