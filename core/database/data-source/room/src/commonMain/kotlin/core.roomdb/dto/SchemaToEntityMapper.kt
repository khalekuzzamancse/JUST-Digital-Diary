@file:Suppress("unused")
package core.roomdb.dto

import core.roomdb.entity.DepartmentEntity
import core.roomdb.entity.DepartmentSubEntity
import core.roomdb.entity.FacultyEntity
import core.roomdb.entity.FacultyMemberEntity
import core.roomdb.schema.DepartmentSchema
import core.roomdb.schema.DepartmentSubSchema
import core.roomdb.schema.FacultyMemberSchema
import core.roomdb.schema.FacultySchema

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
            departments = schema.departments.map {
                fromDepartmentSchema(
                    it
                )
            } // Mapping each DepartmentSchema to database.entity.DepartmentEntity
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
