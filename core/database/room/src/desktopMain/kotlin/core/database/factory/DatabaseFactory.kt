@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package core.database.factory

import core.database.getDatabase

actual class DatabaseFactory {
    actual fun createAcademicApi(): core.database.apis.AcademicApi {
        return AcademicApiImpl(
            facultyDao = getDatabase().facultyDao(),
            departmentDao = getDatabase().departmentDao(),
            facultyMemberDao = getDatabase().facultyMemberDao()

        )
    }

    actual fun createAuthApi(): core.database.apis.AuthApi {
        return AuthApiImpl(
            credentialDao = getDatabase().credentialDao()
        )
    }

    actual fun createTokenApi(): core.database.apis.TokenApi {
        return TokenApiImpl(tokenDao = getDatabase().tokenDao())
    }
}