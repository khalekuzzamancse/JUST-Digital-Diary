package core.roomdb.factory

import core.roomdb.apis.AuthApi
import core.roomdb.apis.TokenApi

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class RoomDBFactory {


    actual fun createAuthApi(): AuthApi {
        return AuthApiImpl(
            credentialDao = getDatabase().credentialDao()
        )
    }

    actual fun createTokenApi(): TokenApi {
        return TokenApiImpl(tokenDao = getDatabase().tokenDao())
    }

    actual fun createAcademicApi2(): RoomAcademicApi {
        return RoomAcademicImpl(
            facultyDao = getDatabase().facultyDao(),
            departmentDao = getDatabase().departmentDao(),
            teacherDao = getDatabase().facultyMemberDao()

        )
    }
}