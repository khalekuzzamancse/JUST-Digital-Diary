package com.just.cse.digitaldiary.twozerotwothree.core.di

import com.just.cse.digital_diary.two_zero_two_three.architecture_layers.data.faculties.repoisitory.FacultyListRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository.LoginRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.register.repoisitory.RegisterRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.domain.register.model.RegisterRequestModel
import com.just.cse.digital_diary.two_zero_two_three.domain_layer.login.model.LoginRequestModel


suspend fun diTest() {
//  val res=  RegisterRepositoryImpl().register(
//        model = RegisterRequestModel(
//            name = "khalek05",
//            email = "khalek05@just.edu.bd",
//            username = "khalek05",
//            password = "test@123"
//        )
//    )
//    val res=  LoginRepositoryImpl().login(
//        model = LoginRequestModel(
//            username = "khalek05",
//            password = "test@123"
//        )
//    )
        val res=  FacultyListRepositoryImpl().getFaculties()

    println("DITest:RegisterResult:$res")
}