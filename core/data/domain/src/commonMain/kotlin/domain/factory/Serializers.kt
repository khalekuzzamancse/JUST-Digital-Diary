package domain.factory

import core.data.entity.academic.*

object  Serializers{
        fun facultyReadEntity()= FacultyReadEntity.serializer()
        fun deptReadEntity()= DepartmentReadEntity.serializer()
        fun teacherReadEntity()= TeacherReadEntity.serializer()
    }