package faculty.di

import faculty.data.department.repoisitory.DepartmentListRepositoryImpl
import faculty.data.faculty.repoisitory.FacultyListRepositoryImpl
import faculty.data.teacher.repoisitory.TeacherListRepositoryImpl


/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object FacultyComponentProvider {
    private const val TOKEN="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJiZDI2ODQ3MC1kMmFhLTQxZmEtOSIsInJvbGVfaWQiOjEsImlhdCI6MTcxMTM0MDUzNCwiZXhwIjoxNzExMzgzNzM0fQ.YjVitfZPY-Lzt8zwD2W9RtYoELLQ0lvNyp0CVA_OdpE"

     fun getFacultyRepository(): FacultyListRepositoryImpl {
         return FacultyListRepositoryImpl(TOKEN)
    }
    fun getDepartListRepository(): DepartmentListRepositoryImpl {
        return DepartmentListRepositoryImpl(TOKEN)
    }
    fun getTeacherListRepository(): TeacherListRepositoryImpl {
        return TeacherListRepositoryImpl(TOKEN)
    }

}