package faculty.di

import faculty.data.department.repoisitory.DepartmentListRepositoryImpl
import faculty.data.faculty.repoisitory.FacultyListRepositoryImpl
import faculty.data.teacher.repoisitory.TeacherListRepositoryImpl


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