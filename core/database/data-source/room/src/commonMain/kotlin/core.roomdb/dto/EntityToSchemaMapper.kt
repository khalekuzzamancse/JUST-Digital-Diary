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

internal object EntityToSchemaMapper {



    // Convert database.entity.FacultyEntity to FacultySchema
    fun fromFacultyEntity(entity: FacultyEntity): FacultySchema {
        return FacultySchema(
            facultyId = entity.facultyId,
            id = entity.id,
            name = entity.name,
            departmentCount = entity.departmentCount
        )
    }

    // Convert database.entity.FacultyMemberEntity to FacultyMemberSchema
    fun fromFacultyMemberEntity(entity: FacultyMemberEntity): FacultyMemberSchema {
        return FacultyMemberSchema(
            uid = entity.uid,
            deptId = entity.deptId,
            name = entity.name,
            email = entity.email,
            role = entity.role,
            phone = entity.phone,
            achievement = entity.achievement,
            profile = entity.profile,
            additionalEmail = entity.additionalEmail,
            type = entity.type,
            departments = entity.departments.map {
                fromDepartmentEntity(
                    it
                )
            } // Mapping each database.entity.DepartmentEntity to DepartmentSchema
        )
    }

    // Convert database.entity.DepartmentEntity to Department (Schema)
    fun fromDepartmentEntity(entity: DepartmentSubEntity): DepartmentSubSchema {
        return DepartmentSubSchema(
            name = entity.name,
            shortname = entity.shortname,
            designation = entity.designation,
            roomNo = entity.roomNo,
            present = entity.present
        )
    }

    // Convert database.entity.DepartmentEntity to DepartmentSchema
    fun fromDepartmentSchemaEntity(entity: DepartmentEntity): DepartmentSchema {
        return DepartmentSchema(
            id = entity.id,
            facultyId = entity.facultyId,
            deptId = entity.deptId,
            shortname = entity.shortname,
            name = entity.name,
            membersCount = entity.membersCount
        )
    }
}
