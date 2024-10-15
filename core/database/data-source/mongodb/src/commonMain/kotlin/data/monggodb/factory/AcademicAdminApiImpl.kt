@file:Suppress("unused")

package data.monggodb.factory

import data.monggodb.db.DepartmentCollection
import data.monggodb.db.FacultyCollection
import data.monggodb.db.TeacherCollection
import domain.api.AcademicAdminApi

class AcademicAdminApiImpl internal  constructor(): AcademicAdminApi {

    override suspend fun readAllFaculty() = withExceptionHandle {
        FacultyCollection().getAllFaculties()
    }

    override suspend fun insertFaculty(json: String) = withExceptionHandle {
        FacultyCollection().insert(json)
    }


    override suspend fun readFacultyById(id: String) = withExceptionHandle {
        FacultyCollection().read(id)
    }


    override suspend fun insertDept(facultyId: String, json: String) = withExceptionHandle {
        DepartmentCollection().insert(facultyId = facultyId, json = json)
    }


    override suspend fun readAllDept() = withExceptionHandle {
        DepartmentCollection().readAll()
    }

    override suspend fun deptUnderFaculty(facultyId: String) = withExceptionHandle {
        DepartmentCollection().readUnderFaculty(facultyId)
    }


    override suspend fun readDeptById(id: String) = withExceptionHandle {
        DepartmentCollection().readById(id)
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


}