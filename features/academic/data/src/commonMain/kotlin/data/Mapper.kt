package data

import data.entity.DepartmentListEntity
import data.entity.FacultyEntity
import data.entity.TeacherListEntity
import faculty.domain.model.DepartmentModel
import faculty.domain.model.DepartmentSubModel
import faculty.domain.model.FacultyModel
import faculty.domain.model.TeacherModel

internal object Mapper {
    fun toFacultyModel(entities: List<FacultyEntity>): List<FacultyModel> {
        return entities.map {
            FacultyModel(
                facultyId = it.facultyId,
                name = it.name,
                departmentsCount = it.departmentCount,
                id = it.id,
            )

        }
    }

    fun toDeptModels(entity: DepartmentListEntity): List<DepartmentModel> {
        return entity.departments.map {
            DepartmentModel(
                id = it.id,
                deptId = it.deptId,
                name = it.name,
                shortName = it.shortName,
                employeeCount = it.membersCount
            )

        }
    }
    fun  toTeacherModels(entity: TeacherListEntity): List<TeacherModel> {
        return entity.facultyMembers.map {
            TeacherModel(
                name = it.name,
                email = it.email,
                additionalEmail = it.additional_email,
                phone = it.phone,
                departments = it.departments.map { dept->
                    DepartmentSubModel(
                        name = dept.name,
                        designation = dept.designation,
                        roomNo = dept.room_no,
                        present = dept.present,
                        shortname = dept.shortname
                    )
                },
                role = it.role,
                achievement = it.achievement,
                profile = it.profile,
                type = it.type,
                uid = it.uid,
            )
        }
    }
}
