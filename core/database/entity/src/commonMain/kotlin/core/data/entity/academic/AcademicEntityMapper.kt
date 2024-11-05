package core.data.entity.academic

object AcademicEntityMapper {

    fun departmentReadToWrite(
        readEntity: DepartmentReadEntity
    ): DepartmentWriteEntity {
        return DepartmentWriteEntity(
            priority = readEntity.priority,
            name = readEntity.name,
            shortname = readEntity.shortname
        )
    }

    fun facultyReadToWrite(
        readEntity: FacultyReadEntity
    ): FacultyWriteEntity {
        return FacultyWriteEntity(
            priority = readEntity.priority,
            name = readEntity.name
        )
    }

    fun teacherReadToWrite(
        readEntity: TeacherReadEntity,
    ): TeacherWriteEntity {
        return TeacherWriteEntity(
            priority = readEntity.priority,
            name = readEntity.name,
            email = readEntity.email,
            additional_email = readEntity.additional_email,
            achievements = readEntity.achievements,
            phone = readEntity.phone,
            designations = readEntity.designations,
            room_no = readEntity.room_no,
            image_link = readEntity.image_link
        )
    }
}
