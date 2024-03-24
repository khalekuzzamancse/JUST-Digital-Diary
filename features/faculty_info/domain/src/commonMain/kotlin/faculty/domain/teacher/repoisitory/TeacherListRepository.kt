package faculty.domain.teacher.repoisitory

import faculty.domain.teacher.model.TeacherModel

interface TeacherListRepository{

    suspend fun getTeachers(deptId:String): Result<List<TeacherModel>>

}