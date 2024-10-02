package core.database.factory

import core.database.apis.TokenApi
import core.database.getDatabase

fun createTokenApiDesktop():TokenApi{
    return TokenApiImpl(getDatabase().tokenDao())
}