package core.database.factory

import core.database.apis.TokenApi
import core.database.getDatabase

actual fun createTokenApi(): TokenApi {
   return TokenApiImpl(getDatabase().tokenDao())
}