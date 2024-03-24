package faculty.di

import faculty.data.department.repoisitory.DepartmentListRepositoryImpl
import faculty.data.faculty.repoisitory.FacultyListRepositoryImpl
import faculty.data.teacher.repoisitory.TeacherListRepositoryImpl


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object FacultyComponentProvider {
    private const val TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJiZDI2ODQ3MC1kMmFhLTQxZmEtOSIsInJvbGVfaWQiOjEsImlhdCI6MTcxMTI4ODk5NSwiZXhwIjoxNzExMzMyMTk1fQ.8Vazwh4x5lYFzXoPakm-5Qpnjz5UqIUbg2arlNQ-Ey4"

     fun getFacultyRepository(): FacultyListRepositoryImpl {
       // return FacultyListRepositoryImpl(AuthComponentProvider.authToken)
         return FacultyListRepositoryImpl(TOKEN)
    }
    fun getDepartListRepository(): DepartmentListRepositoryImpl {
      //  return DepartmentListRepositoryImpl(AuthComponentProvider.authToken)
        return DepartmentListRepositoryImpl(TOKEN)
    }
    fun getTeacherListRepository(): TeacherListRepositoryImpl {
//        return TeacherListRepositoryImpl(AuthComponentProvider.authToken)
        return TeacherListRepositoryImpl(TOKEN)
    }

}