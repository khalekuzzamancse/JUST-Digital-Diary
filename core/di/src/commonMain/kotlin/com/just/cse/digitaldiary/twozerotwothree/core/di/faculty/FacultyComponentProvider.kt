package com.just.cse.digitaldiary.twozerotwothree.core.di.faculty

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.departments.repoisitory.DepartmentListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.repoisitory.FacultyListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.teachers.repoisitory.TeacherListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository.LoginRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.register.repoisitory.RegisterRepositoryImpl
import com.just.cse.digitaldiary.twozerotwothree.core.di.auth.AuthComponentProvider

/**
 ** Instead of storing the resource we are returning it
 * so the client manually handle the lifecycle of the [LoginRepositoryImpl]
 */
object FacultyComponentProvider {

     fun getFacultyRepository(): FacultyListRepositoryImpl{
        return FacultyListRepositoryImpl(AuthComponentProvider.authToken)
    }
    fun getDepartListRepository(): DepartmentListRepositoryImpl {
        return DepartmentListRepositoryImpl(AuthComponentProvider.authToken)
    }
    fun getTeacherListRepository(): TeacherListRepositoryImpl {
        return TeacherListRepositoryImpl(AuthComponentProvider.authToken)
    }

}