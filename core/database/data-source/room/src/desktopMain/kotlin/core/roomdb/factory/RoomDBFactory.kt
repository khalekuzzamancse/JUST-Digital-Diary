package core.roomdb.factory

import core.roomdb.apis.AcademicApiRoom
import core.roomdb.apis.AuthApi
import core.roomdb.apis.TokenApi
import domain.api.AcademicApi

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class RoomDBFactory {
    actual fun createAcademicApi(): AcademicApiRoom {
        return AcademicApiImpl(
            facultyDao = getDatabase().facultyDao(),
            departmentDao = getDatabase().departmentDao(),
            facultyMemberDao = getDatabase().facultyMemberDao()

        )
    }

    actual fun createAuthApi(): AuthApi {
        return AuthApiImpl(
            credentialDao = getDatabase().credentialDao()
        )
    }

    actual fun createTokenApi(): TokenApi {
        return TokenApiImpl(tokenDao = getDatabase().tokenDao())
    }

    actual fun createAcademicApi2(): AcademicApi {
        return AcademicApiImpl2(
            facultyDao = getDatabase().facultyDao(),
            departmentDao = getDatabase().departmentDao(),
            facultyMemberDao = getDatabase().facultyMemberDao()

        )
    }
}