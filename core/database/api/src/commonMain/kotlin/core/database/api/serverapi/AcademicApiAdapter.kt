package core.database.api.serverapi

internal object AcademicApiAdapter {
    fun FacultyReadEntity.convert() = domain.entity.academic.FacultyReadEntity(
        priority = id,
        faculty_id = facultyId,
        name = name,
        number_of_dept = departmentCount
    )

    fun DepartmentEntity.convert(facultyId: String) = domain.entity.academic.DepartmentReadEntity(
        priority = id,
        dept_id = deptId,
        name = name,
        shortname = shortName,
        number_of_employee = membersCount,
        faculty_id = facultyId
    )

    fun TeacherEntity.convert(deptId: String): domain.entity.academic.TeacherReadEntity {
        val moreInfo = departments.first()//Can throw exception
        return domain.entity.academic.TeacherReadEntity(
            id = uid,
            dept_id = deptId,
            name = name,
            email = email,
            additional_email = additional_email,
            phone = phone,
            priority = type,
            achievements = achievement,
            image_link = profile,
            room_no = moreInfo.room_no,
            designations = moreInfo.designation
        )
    }
}
