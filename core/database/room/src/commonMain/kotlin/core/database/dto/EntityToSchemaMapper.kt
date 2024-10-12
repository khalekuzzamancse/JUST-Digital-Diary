@file:Suppress("unused")
package core.database.dto

import core.database.entity.DepartmentEntity
import core.database.entity.DepartmentSubEntity
import core.database.entity.FacultyEntity
import core.database.entity.FacultyMemberEntity
import core.database.schema.DepartmentSchema
import core.database.schema.DepartmentSubSchema
import core.database.schema.FacultyMemberSchema
import core.database.schema.FacultySchema

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
