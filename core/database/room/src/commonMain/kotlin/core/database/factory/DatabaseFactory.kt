package core.database.factory

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class DatabaseFactory {
    fun createAcademicApi(): core.database.apis.AcademicApi
    fun createAuthApi(): core.database.apis.AuthApi
    fun createTokenApi(): core.database.apis.TokenApi
}