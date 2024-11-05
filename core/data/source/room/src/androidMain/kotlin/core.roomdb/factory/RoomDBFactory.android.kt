package core.roomdb.factory

import domain.api.AcademicCacheApi
import domain.api.AdministrationCacheApi

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class RoomDBFactory {
    actual fun createAcademicCacheApi(): AcademicCacheApi {
        return AcademicRommCache(
            facultyDao = RoomDBProvider.db.facultyDao(),
            departmentDao =  RoomDBProvider.db.departmentDao(),
            teacherDao =  RoomDBProvider.db.facultyMemberDao(),
        )
    }

    actual fun createAdministrationCacheApi(): AdministrationCacheApi {
        return AdministrationRoomCache(
            officeDao =  RoomDBProvider.db.officeDao(),
            subOfficeDao =  RoomDBProvider.db.subOfficeDao(),
            employeeDao =  RoomDBProvider.db.adminEmployeeDao()
        )
    }
}