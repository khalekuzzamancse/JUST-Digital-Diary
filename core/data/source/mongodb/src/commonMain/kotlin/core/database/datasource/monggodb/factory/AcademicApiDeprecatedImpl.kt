@file:Suppress("unused")

package core.database.datasource.monggodb.factory

import core.database.datasource.monggodb.core.withExceptionHandle
import core.database.datasource.monggodb.db.DepartmentCollection
import core.database.datasource.monggodb.db.FacultyCollection
import core.database.datasource.monggodb.db.TeacherCollection
import domain.api.AcademicApiDeprecated

class AcademicApiDeprecatedImpl internal  constructor(): AcademicApiDeprecated {

    override suspend fun readFaculties() = withExceptionHandle {
        FacultyCollection().getAllFaculties()
    }

    override suspend fun insertFaculty(json: String): String {
        TODO("Not yet implemented")
    }

    override suspend fun upsetFacultyList(json: String) = withExceptionHandle {
        FacultyCollection().insert(json)
    }


    override suspend fun readFacultyById(id: String) = withExceptionHandle {
        FacultyCollection().read(id)
    }

    override suspend fun deleteFaculty(id: String)=withExceptionHandle {
        FacultyCollection().deleteOrThrow(id)
    }



    //TODO:Departments Related

    override suspend fun insertDept(facultyId: String, json: String) = withExceptionHandle {
        DepartmentCollection().insert(facultyId = facultyId, json = json)
    }


    override suspend fun readAllDept() = withExceptionHandle {
        DepartmentCollection().readAll()
    }

    override suspend fun readDeptsUnderFaculty(facultyId: String) = withExceptionHandle {
        DepartmentCollection().readUnderFaculty(facultyId)
    }


    override suspend fun readDeptById(id: String) = withExceptionHandle {
        DepartmentCollection().readById(id)
    }

    override suspend fun deleteDepartment(id: String)= withExceptionHandle {
        DepartmentCollection().deleteOrThrow(id)
    }

    override suspend fun insertTeacher(deptId: String, json: String)= withExceptionHandle {
        TeacherCollection().insert(deptId = deptId, json = json)
    }


    override suspend fun readAllTeacher()= withExceptionHandle {
        TeacherCollection().readAll()
    }

    override suspend fun readTeachersUnderDept(deptId: String)= withExceptionHandle {
        TeacherCollection().readByDeptId(deptId)
    }

    override suspend fun readTeacherById(teacherId: String)= withExceptionHandle {
        TeacherCollection().readById(teacherId)
    }

    override suspend fun updateFaculty(facultyId: String, json: String)= withExceptionHandle {
        FacultyCollection().updateOrThrow(facultyId = facultyId, json = json)
    }

    override suspend fun updateDept(deptId: String, json: String)= withExceptionHandle {
        DepartmentCollection().updateOrThrow(deptId = deptId, json = json)
    }
    override suspend fun updateTeacher(teacherId: String, json: String)= withExceptionHandle {
        TeacherCollection().updateOrThrow(teacherId = teacherId, json = json)
    }

    override suspend fun deleteTeacher(id: String)= withExceptionHandle {
        TeacherCollection().deleteOrThrow(id)
    }

}