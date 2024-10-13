package core.database.monggodb.di

import core.database.monggodb.data.services.JsonParser
import core.database.monggodb.data.services.JsonParserImpl
import core.database.monggodb.presentation.DepartmentApi
import core.database.monggodb.presentation.DepartmentApiImpl
import core.database.monggodb.presentation.FacultyApi
import core.database.monggodb.presentation.FacultyApiImpl
import core.database.monggodb.presentation.TeacherApi
import core.database.monggodb.presentation.TeacherApiImpl
import kotlinx.serialization.json.Json

object DBFactory {

    fun facultyApi(): FacultyApi = FacultyApiImpl()
    fun departmentApi(): DepartmentApi = DepartmentApiImpl()
    fun teacherApi(): TeacherApi = TeacherApiImpl()
    internal fun jsonParser(): JsonParser = JsonParserImpl()
    internal fun jsonParser2()=Json {
        prettyPrint = true
        encodeDefaults = true  // Include fields with default values
    }
}