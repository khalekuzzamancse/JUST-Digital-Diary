package core.database.api

object ApiFactory {
    fun academicAdminApi():AcademicAdminApi=MongoAcademicAdminApi()
}