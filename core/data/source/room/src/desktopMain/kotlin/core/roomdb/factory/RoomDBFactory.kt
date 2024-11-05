package core.roomdb.factory

import domain.api.AcademicCacheApi
import domain.api.AdministrationCacheApi

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class RoomDBFactory {

    actual fun createAcademicCacheApi(): AcademicCacheApi {
        return AcademicRommCache(
            facultyDao = getDatabase().facultyDao(),
            departmentDao = getDatabase().departmentDao(),
            teacherDao = getDatabase().facultyMemberDao(),
        )
    }

    actual fun createAdministrationCacheApi(): AdministrationCacheApi {
        return AdministrationRoomCache(
            officeDao = getDatabase().officeDao(),
            subOfficeDao = getDatabase().subOfficeDao(),
            employeeDao = getDatabase().adminEmployeeDao()
        )
    }
}