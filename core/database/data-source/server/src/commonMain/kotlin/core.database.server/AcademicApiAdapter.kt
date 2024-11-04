package core.database.server
import core.data.entity.academic.*
internal object AcademicApiAdapter {
    fun FacultyReadEntity.convert() =FacultyReadEntity(
        priority = id,
        faculty_id = facultyId,
        name = name,
        number_of_dept = departmentCount
    )

    fun DepartmentEntity.convert(facultyId: String) =DepartmentReadEntity(
        priority = id,
        dept_id = deptId,
        name = name,
        shortname = shortName,
        number_of_employee = membersCount,
        faculty_id = facultyId
    )

    fun TeacherEntity.convert(deptId: String): TeacherReadEntity {
        val moreInfo = departments.first()//Can throw exception
        return TeacherReadEntity(
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
