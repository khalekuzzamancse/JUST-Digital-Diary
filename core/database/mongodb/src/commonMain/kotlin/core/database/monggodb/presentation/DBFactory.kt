package core.database.monggodb.presentation

import core.database.monggodb.presentation.apis.DepartmentApi
import core.database.monggodb.presentation.apis.DepartmentApiImpl
import core.database.monggodb.presentation.apis.FacultyApi
import core.database.monggodb.presentation.apis.FacultyApiImpl
import core.database.monggodb.presentation.apis.TeacherApi
import core.database.monggodb.presentation.apis.TeacherApiImpl
import core.database.monggodb.services.JsonParser
import core.database.monggodb.services.JsonParserImpl

object DBFactory {

    fun facultyApi(): FacultyApi = FacultyApiImpl()
    fun departmentApi(): DepartmentApi = DepartmentApiImpl()
    fun teacherApi(): TeacherApi = TeacherApiImpl()
    internal fun jsonParser(): JsonParser = JsonParserImpl()
}