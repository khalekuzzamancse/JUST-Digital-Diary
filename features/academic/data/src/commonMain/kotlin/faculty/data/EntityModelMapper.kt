@file:Suppress("unused")

package faculty.data

import faculty.data.entity.DepartmentReadEntity
import faculty.data.entity.DepartmentWriteEntity
import faculty.data.entity.FacultyReadEntity
import faculty.data.entity.FacultyWriteEntity
import faculty.data.entity.TeacherReadEntity
import faculty.data.entity.TeacherWriteEntity
import faculty.domain.model.TeacherReadModel
import faculty.domain.model.DepartmentReadModel
import faculty.domain.model.DepartmentWriteModel
import faculty.domain.model.FacultyReadModel
import faculty.domain.model.FacultyWriteModel
import faculty.domain.model.TeacherWriteModel

/**
 * Need to send down the priorities to the UI,so that admin can use it while updating
 */
internal object EntityModelMapper {
    fun FacultyReadEntity.toModel() = FacultyReadModel(
        facultyId = faculty_id,
        name = name,
        departmentsCount = number_of_dept,
        priority = priority
    )

    fun DepartmentReadEntity.toModel() = DepartmentReadModel(
        name = name,
        shortname = shortname,
        deptId = dept_id,
        numberOfEmployee = number_of_employee,
        facultyId = faculty_id,
        priority = priority
    )

    fun TeacherReadEntity.toModel() = TeacherReadModel(
        name = name,
        id = id,
        deptId = dept_id,
        email = email,
        additionalEmail = additional_email,
        phone = phone,
        achievements = achievements,
        roomNo = room_no,
        imageLink = image_link,
        designations = designations,
        priority = priority

    )


    fun FacultyWriteModel.toEntity() = FacultyWriteEntity(
        priority = this.priority,
        name = this.name,
    )

    fun DepartmentWriteModel.toEntity() = DepartmentWriteEntity(
        priority = this.priority,
        name = this.name,
        shortname = this.shortname,
    )

    fun TeacherWriteModel.toEntity() = TeacherWriteEntity(
        priority = priority,
        name = name,
        email = email,
        additional_email = additionalEmail?.ifBlank { null },
        achievements = achievements,
        phone = phone,
        designations = designations,
        room_no = roomNo?.ifBlank { null },
        image_link = profileImageLink
    )


}