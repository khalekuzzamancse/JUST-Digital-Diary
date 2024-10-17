@file:Suppress("unused")
package data

import data.entity2.DepartmentReadEntity
import data.entity2.FacultyReadEntity
import data.entity2.TeacherReadEntity
import faculty.domain.model.TeacherReadModel
import faculty.domain.model.DepartmentReadModel
import faculty.domain.model.FacultyReadModel

/**
 * Need to send down the priorities to the UI,so that admin can use it while updating
 */
internal object ReadEntityModelMapper {
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

}