@file:Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

package database.factory

import database.apis.AcademicApi
import database.apis.AuthApi
import database.getDatabase

actual class DatabaseFactory {
    actual fun createAcademicApi(): AcademicApi {
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
}