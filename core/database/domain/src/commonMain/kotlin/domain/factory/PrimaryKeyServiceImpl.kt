@file:Suppress("unused")
package domain.factory

import domain.entity.DepartmentWriteEntity
import domain.entity.FacultyWriteEntity
import domain.entity.TeacherWriteEntity
import domain.service.PrimaryKeyService


class PrimaryKeyServiceImpl internal constructor(): PrimaryKeyService {

    /**
     * Generate primary key for faculty and validate the write entity.
     * @param json pass the [FacultyWriteEntity] in JSON format.
     * @return the generated primary key for the faculty.
     **/
    override fun getFacultyKeyOrThrow(json: String): String {
        val faculty = parser().parseOrThrow(json, FacultyWriteEntity.serializer())
        // Sure that no two faculty have the same name, so it can be used as the primary key
        val primaryKey = faculty.name.replace(" ", "").lowercase()
        return primaryKey
    }

    /**
     * Generate primary key for department and validate the write entity.
     * @param json pass the [DepartmentWriteEntity] in JSON format.
     * @return the generated primary key for the department.
     **/
    override fun getDepartmentKeyOrThrow(json: String): String {
        val dept = parser().parseOrThrow(json, DepartmentWriteEntity.serializer())
        // Sure that no two departments have the same name, so it can be used as the primary key
        val primaryKey = dept.name.replace(" ", "").lowercase()
        return primaryKey
    }

    /**
     * Generate primary key for teacher and validate the write entity.
     * @param json pass the [TeacherWriteEntity] in JSON format.
     * @return the generated primary key for the teacher.
     **/
    override fun getTeacherKeyOrThrow(json: String): String {
        val teacher = parser().parseOrThrow(json, TeacherWriteEntity.serializer())
        // Name+email combination is unique
        val primaryKey = teacher.name.replace(" ", "").lowercase() + teacher.email.replace(" ", "").lowercase()
        //TODO: Email might be changed, so refactor this later
        return primaryKey
    }

    private fun parser() = ContractFactory.jsonParser()
}
