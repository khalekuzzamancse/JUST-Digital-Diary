package core.roomdb.factory

import domain.api.AcademicCacheApi
import domain.api.AdministrationCacheApi

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
 expect class RoomDBFactory {
    fun createAcademicCacheApi(): AcademicCacheApi
    fun createAdministrationCacheApi():AdministrationCacheApi
}