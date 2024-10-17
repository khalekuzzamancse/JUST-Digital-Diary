package domain.factory

import domain.entity.academic.DepartmentReadEntity
import domain.entity.academic.FacultyReadEntity
import domain.entity.academic.TeacherReadEntity

object  Serializers{
        fun facultyReadEntity()= FacultyReadEntity.serializer()
        fun deptReadEntity()= DepartmentReadEntity.serializer()
        fun teacherReadEntity()= TeacherReadEntity.serializer()
    }