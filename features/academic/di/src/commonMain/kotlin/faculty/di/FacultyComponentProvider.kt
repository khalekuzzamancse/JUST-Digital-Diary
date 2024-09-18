package faculty.di

import academic.data.department.repoisitory.DepartmentListRepositoryImpl
import academic.data.faculty.repoisitory.FacultyListRepositoryImpl
import academic.data.teacher.repoisitory.TeacherListRepositoryImpl


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object FacultyComponentProvider {
     fun getFacultyRepository(): FacultyListRepositoryImpl {
         return FacultyListRepositoryImpl()
    }
    fun getDepartListRepository(): DepartmentListRepositoryImpl {
        return DepartmentListRepositoryImpl()
    }
    fun getTeacherListRepository(): TeacherListRepositoryImpl {
        return TeacherListRepositoryImpl()
    }


}