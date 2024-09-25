@file:Suppress("unused")
package database.dto

import database.entity.DepartmentEntity
import database.entity.DepartmentSubEntity
import database.entity.FacultyEntity
import database.entity.FacultyMemberEntity
import database.schema.DepartmentSubSchema
import database.schema.DepartmentSchema
import database.schema.FacultyMemberSchema
import database.schema.FacultySchema
import database.schema.TokenSchema

internal
object SchemaToEntityMapper {



    // Convert FacultySchema to database.entity.FacultyEntity
    fun fromFacultySchema(schema: FacultySchema): FacultyEntity {
        return FacultyEntity(
            facultyId = schema.facultyId,
            id = schema.id,
            name = schema.name,
            departmentCount = schema.departmentCount
        )
    }

    // Convert FacultyMemberSchema to database.entity.FacultyMemberEntity
    fun fromFacultyMemberSchema(schema: FacultyMemberSchema): FacultyMemberEntity {
        return FacultyMemberEntity(
            uid = schema.uid,
            deptId = schema.deptId,
            name = schema.name,
            email = schema.email,
            role = schema.role,
            phone = schema.phone,
            achievement = schema.achievement,
            profile = schema.profile,
            additionalEmail = schema.additionalEmail,
            type = schema.type,
            departments = schema.departments.map { fromDepartmentSchema(it) } // Mapping each DepartmentSchema to database.entity.DepartmentEntity
        )
    }

    // Convert DepartmentSchema to database.entity.DepartmentEntity
    fun fromDepartmentSchema(schema: DepartmentSubSchema): DepartmentSubEntity {
        return DepartmentSubEntity(
            name = schema.name,
            shortname = schema.shortname,
            designation = schema.designation,
            roomNo = schema.roomNo,
            present = schema.present
        )
    }

    // Convert DepartmentSchema to database.entity.DepartmentSchemaEntity
    fun fromDepartmentEntity(schema: DepartmentSchema): DepartmentEntity {
        return DepartmentEntity(
            id = schema.id,
            facultyId = schema.facultyId,
            deptId = schema.deptId,
            shortname = schema.shortname,
            name = schema.name,
            membersCount = schema.membersCount
        )
    }
    fun fromDepartmentSchemaEntity(){

    }
}
