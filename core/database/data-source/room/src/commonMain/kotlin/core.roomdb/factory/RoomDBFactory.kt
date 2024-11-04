package core.roomdb.factory

import core.roomdb.apis.AuthApi
import core.roomdb.apis.TokenApi
import domain.api.AcademicApi

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
 expect class RoomDBFactory {
    fun createAuthApi(): AuthApi
    fun createTokenApi(): TokenApi
    fun createAcademicApi2():AcademicApi
}