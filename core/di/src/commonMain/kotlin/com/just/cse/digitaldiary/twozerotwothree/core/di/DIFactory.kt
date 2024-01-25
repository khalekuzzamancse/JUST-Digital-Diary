package com.just.cse.digitaldiary.twozerotwothree.core.di

import com.just.cse.digital_diary.two_zero_two_three.data_layer.login.repository.LoginRepositoryImpl
import com.just.cse.digital_diary.two_zero_two_three.data_layer.register.repoisitory.RegisterRepositoryImpl

class DIFactory {

    fun getLoginRepository():LoginRepositoryImpl{
        return LoginRepositoryImpl()
    }
    fun getRegisterRepository():RegisterRepositoryImpl{
        return  RegisterRepositoryImpl()
    }
}