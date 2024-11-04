@file:Suppress("unused")

package faculty.data

import core.data.entity.academic.*
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

    fun toModel(entity: FacultyReadEntity): FacultyReadModel = FacultyReadModel(

        facultyId = entity.faculty_id,
        name = entity.name,
        departmentsCount = entity.number_of_dept,
        priority = entity.priority
    )

    fun toModel(entity: DepartmentReadEntity): DepartmentReadModel = DepartmentReadModel(
        name = entity.name,
        shortname = entity.shortname,
        deptId = entity.dept_id,
        numberOfEmployee = entity.number_of_employee,
        facultyId = entity.faculty_id,
        priority = entity.priority
    )

    fun toModel(entity: TeacherReadEntity): TeacherReadModel = TeacherReadModel(
        name = entity.name,
        id = entity.id,
        deptId = entity.dept_id,
        email = entity.email,
        additionalEmail = entity.additional_email,
        phone = entity.phone,
        achievements = entity.achievements,
        roomNo = entity.room_no,
        imageLink = entity.image_link,
        designations = entity.designations,
        priority = entity.priority
    )

    fun toEntity(model: FacultyWriteModel): FacultyWriteEntity = FacultyWriteEntity(
        priority = model.priority,
        name = model.name,
    )

    fun toEntity(model: DepartmentWriteModel): DepartmentWriteEntity = DepartmentWriteEntity(
        priority = model.priority,
        name = model.name,
        shortname = model.shortname,
    )

    fun toEntity(model: TeacherWriteModel): TeacherWriteEntity = TeacherWriteEntity(
        priority = model.priority,
        name = model.name,
        email = model.email,
        additional_email = model.additionalEmail?.ifBlank { null },
        achievements = model.achievements,
        phone = model.phone,
        designations = model.designations,
        room_no = model.roomNo?.ifBlank { null },
        image_link = model.profileImageLink
    )
}
